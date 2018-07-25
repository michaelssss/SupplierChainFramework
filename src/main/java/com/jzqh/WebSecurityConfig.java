package com.jzqh;

import com.jzqh.account.CustomerAuthFailedHandler;
import com.jzqh.account.CustomerAuthSuccessHandler;
import com.jzqh.account.JSONUsernamePasswordAuthenticationFilter;
import com.jzqh.account.UserDetailsServiceImpl;
import com.jzqh.account.accessmanagement.URLVoter;
import com.jzqh.configuration.ConfigurationCenter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.access.AccessDecisionVoter;
import org.springframework.security.access.vote.AffirmativeBased;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.util.ArrayList;
import java.util.List;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Autowired
    private UserDetailsServiceImpl service;

    @Autowired
    private ConfigurationCenter configurationCenter;

    @Override
    public void configure(WebSecurity web) {
        web.ignoring().antMatchers("/v2/api-docs",
                "/configuration/ui",
                "/swagger-resources",
                "/configuration/security",
                "/swagger-ui.html",
                "/webjars/**",
                "/login.html");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(service).passwordEncoder(new Pbkdf2PasswordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        List<AccessDecisionVoter<? extends Object>> voters = new ArrayList<>();
        voters.add(new URLVoter());
        http
                .cors().and()
                .antMatcher("/login**").authorizeRequests().accessDecisionManager(new AffirmativeBased(voters))
                .antMatchers("/login**").permitAll()
                .anyRequest().authenticated()
                //这里必须要写formLogin()，不然原有的UsernamePasswordAuthenticationFilter不会出现，也就无法配置我们重新的UsernamePasswordAuthenticationFilter
                .and().formLogin().and().csrf().disable();

        //用重写的Filter替换掉原有的UsernamePasswordAuthenticationFilter
        http.addFilterAt(customAuthenticationFilter(),
                UsernamePasswordAuthenticationFilter.class);
    }

    //注册自定义的UsernamePasswordAuthenticationFilter
    @Bean
    JSONUsernamePasswordAuthenticationFilter customAuthenticationFilter() throws Exception {
        buildDefaultLoginConfig();
        JSONUsernamePasswordAuthenticationFilter filter = new JSONUsernamePasswordAuthenticationFilter();
        filter.setFilterProcessesUrl("/login");
        filter.setAuthenticationFailureHandler(new CustomerAuthFailedHandler(configurationCenter.getValue("auth_fail_url")));
        filter.setAuthenticationSuccessHandler(new CustomerAuthSuccessHandler(configurationCenter.getValue("auth_success_url")));
        //这句很关键，重用WebSecurityConfigurerAdapter配置的AuthenticationManager，不然要自己组装AuthenticationManager
        filter.setAuthenticationManager(authenticationManagerBean());
        return filter;
    }

    private void buildDefaultLoginConfig() {
        if (null == configurationCenter.getValue("auth_fail_url")) {
            configurationCenter.addKeyValue("auth_fail_url", "http://127.0.0.1/login.html");
        }
        if (null == configurationCenter.getValue("auth_success_url")) {
            configurationCenter.addKeyValue("auth_success_url", "http://127.0.0.1/HelloWorld");
        }
    }
}

