package com.jzqh.account;

import com.jzqh.base.Response;
import com.jzqh.utils.JSON;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "authorityFilter", urlPatterns = "/*")
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
        if (!request1.getRequestURI().equals("/User/login") && !user.hasAuthority(uri)) {
            response1.setStatus(HttpServletResponse.SC_FORBIDDEN);
            response1.getWriter().write(JSON.toJSONString(Response.NonOK("user has no right access " + uri)));
            return;
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
