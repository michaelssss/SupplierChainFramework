package com.michaelssss.filter;

import com.michaelssss.account.User;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * 该Filter用于记录所有外部请求的日志
 *
 * @author Michaelssss
 * @date 2018/10/11
 */
@Slf4j
@WebFilter(filterName = "logFilter", urlPatterns = "/*")
public class LogFilter implements Filter {

  @Override
  public void init(FilterConfig filterConfig) throws ServletException {
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
      throws IOException, ServletException {
    HttpServletRequest req = (HttpServletRequest) request;
    HttpServletResponse resp = (HttpServletResponse) response;
    String actionObject = "";
    User user = (User) req.getSession().getAttribute("user");
    if (user != null) {
      actionObject = user.getUsername();
    } else {
      actionObject = req.getRemoteHost();
    }
    log.info(actionObject + " do request " + req.getRequestURI());
    chain.doFilter(request, response);
  }

  @Override
  public void destroy() {
  }
}
