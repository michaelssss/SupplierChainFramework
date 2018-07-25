package com.jzqh.account;

import com.jzqh.base.Response;
import com.jzqh.utils.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomerAuthFailedHandler extends SimpleUrlAuthenticationFailureHandler {
    public CustomerAuthFailedHandler(String defaultFailureUrl) {
        super(defaultFailureUrl);
    }

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        if (null != request.getContentType() && MediaType.APPLICATION_JSON_UTF8.toString().contains(request.getContentType())) {
            logger.debug("" + exception);
            response.getWriter().write(JSON.toJSONString(Response.NonOK("AuthFailed")));
        } else {
            super.onAuthenticationFailure(request, response, exception);
        }
    }
}
