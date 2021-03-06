package com.michaelssss.filter;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.Token;
import com.michaelssss.account.TokenCatalog;
import com.michaelssss.base.Response;
import com.michaelssss.utils.JSON;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.Example;

/**
 * 本类用于验证Header中的Token是否有效
 *
 * @author Michaelssss
 * @date 2018年10月11日
 */
@WebFilter(filterName = "tokenValidateFilter", urlPatterns = "/*")
public class TokenValidateFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest request1 = (HttpServletRequest) request;
    HttpServletResponse response1 = (HttpServletResponse) response;
    String uri = request1.getRequestURI();
    String token = "";
    if (null != request1.getCookies()) {
      for (Cookie cookie : request1.getCookies()) {
        if ("token".equals(cookie.getName())) {
          token = cookie.getValue();
        }
      }
    }
    boolean isSwaggerUri =
        "/swagger-ui.html".equals(uri)
            || uri.matches("^/webjars/springfox-swagger-ui/.*$")
            || "/v2/api-docs".equals(uri)
            || "/swagger-resources".equals(uri)
            || "/configuration/ui".equals(uri);
    if (!isSwaggerUri) {
      if (!"/User/login".equals(request1.getRequestURI())) {
        if (headerHasNoToken(token) || !tokenExist(token) || !tokenNotOutdate(token)) {
          response1.setContentType("application/json");
          response1.getWriter().write(JSON.toJSONString(Response.TokenValidateFailed()));
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
    return catalog.findOne(Example.of(example)).get();
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
