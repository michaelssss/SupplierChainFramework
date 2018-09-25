package com.michaelssss.filter;

import com.michaelssss.account.User;
import com.michaelssss.base.Response;
import com.michaelssss.utils.JSON;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authorityFilter", urlPatterns = "/*")
@Slf4j
public class AuthorityFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        User user = (User) request1.getSession(true).getAttribute("user");
        String uri = request1.getRequestURI();
        boolean isSwaggerUri = "/swagger-ui.html".equals(uri) || "^/webjars/springfox-swagger-ui/.*$".matches(uri) || "/v2/api-docs".equals(uri)
                || "/swagger-resources".equals(uri) || "/configuration/ui".equals(uri);
        if (!isSwaggerUri && !"/User/login".equals(request1.getRequestURI()) && !user.hasAuthority(uri)) {
            log.info("user" + user.getUsername() + " ,try to access" + request1.getRequestURI() + " ,ip=" + request1.getRemoteAddr());
            response1.setStatus(HttpServletResponse.SC_OK);
            response1.getWriter().write(JSON.toJSONString(Response.NonOK("user has no right access " + uri)));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
