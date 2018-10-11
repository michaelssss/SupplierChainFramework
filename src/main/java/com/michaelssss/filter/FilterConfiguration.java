package com.michaelssss.filter;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 本类用于注册Filter至SpringMVC的Dispatcher
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
@Configuration
public class FilterConfiguration {

  @Bean
  public FilterRegistrationBean tokenFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new TokenValidateFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setOrder(1); // order的数值越小 则优先级越高
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean authorityFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new AuthorityFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setOrder(2); // order的数值越小 则优先级越高
    return filterRegistrationBean;
  }

  @Bean
  public FilterRegistrationBean logFilter() {
    FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
    filterRegistrationBean.setFilter(new LogFilter());
    filterRegistrationBean.addUrlPatterns("/*");
    filterRegistrationBean.setOrder(3); // order的数值越小 则优先级越高
    return filterRegistrationBean;
  }
}
