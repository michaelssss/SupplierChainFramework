package com.michaelssss.account;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.base.Response;
import com.michaelssss.utils.JSON;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
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
        String uri = request1.getRequestURI();
        String token = "";
        if (null != request1.getCookies()) {
            for (Cookie cookie : request1.getCookies()) {
                if (cookie.getName().equals("token")) {
                    token = cookie.getValue();
                }
            }
        }
        boolean isSwaggerUri = uri.equals("/swagger-ui.html") || uri.matches("^/webjars/springfox-swagger-ui/.*$") || uri.equals("/v2/api-docs")
                || uri.equals("/swagger-resources") || uri.equals("/configuration/ui");
        if (!isSwaggerUri) {
            if (!request1.getRequestURI().equals("/User/login")) {
                if (headerHasNoToken(token) || !tokenExist(token) || !tokenNotOutdate(token)) {
                    response1.setContentType("application/json");
                    response1.getWriter().write(JSON.toJSONString(Response.NonOK("token validate failed")));
                    response1.setStatus(HttpServletResponse.SC_OK);
                    return;
                } else {
                    Token token1 = getToken(token);
                    ((HttpServletRequest) request).getSession(true).setAttribute("user", token1.getUser());
                }
            }
        }
        chain.doFilter(request, response);
    }

    private boolean tokenNotOutdate(String token) {
        return getToken(token).ifOutdate();
    }

    private boolean headerHasNoToken(String token) {
        return StringUtils.isEmpty(token);
    }

    private Token getToken(String token) {
        TokenCatalog catalog = SpringContextHolder.getBean(TokenCatalog.class);
        Token example = new Token();
        example.setToken(token);
        return catalog.findOne(Example.of(example));
    }

    private boolean tokenExist(String token) {
        TokenCatalog catalog = SpringContextHolder.getBean(TokenCatalog.class);
        Token example = new Token();
        example.setToken(token);
        return catalog.count(Example.of(example)) > 0L;
    }

    @Override
    public void destroy() {

    }
}
