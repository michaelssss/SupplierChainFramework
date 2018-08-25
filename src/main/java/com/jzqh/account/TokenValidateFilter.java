package com.jzqh.account;

import com.jzqh.SpringContextHolder;
import com.jzqh.base.Response;
import com.jzqh.utils.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "tokenValidateFilter", urlPatterns = "/*")
public class TokenValidateFilter implements Filter {


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request1 = (HttpServletRequest) request;
        HttpServletResponse response1 = (HttpServletResponse) response;
        String token = request1.getHeader("token");
        if (!request1.getRequestURI().equals("/User/login")) {
            if (isHeaderHasToken(token) && isTokenExist(token)) {
                response1.setStatus(HttpServletResponse.SC_FORBIDDEN);
                response1.getWriter().write(JSON.toJSONString(Response.NonOK("Not login")));
            } else {
                Token token1 = getToken(token);
                ((HttpServletRequest) request).getSession(true).setAttribute("user", token1.getUser());
            }
        }
        chain.doFilter(request, response);
    }

    private boolean isHeaderHasToken(String token) {
        return StringUtils.isEmpty(token);
    }

    private Token getToken(String token) {
        TokenCatalog catalog = SpringContextHolder.getBean(TokenCatalog.class);
        Token example = new Token();
        example.setToken(token);
        return catalog.findOne(Example.of(example));
    }

    private boolean isTokenExist(String token) {
        TokenCatalog catalog = SpringContextHolder.getBean(TokenCatalog.class);
        Token example = new Token();
        example.setToken(token);
        return catalog.count(Example.of(example)) > 0L;
    }

    @Override
    public void destroy() {

    }
}
