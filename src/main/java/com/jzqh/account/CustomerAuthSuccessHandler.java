package com.jzqh.account;

import com.jzqh.base.Response;
import com.jzqh.utils.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class CustomerAuthSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
    public CustomerAuthSuccessHandler(String defaultTargetUrl) {
        super(defaultTargetUrl);
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        if (null != request.getContentType() && MediaType.APPLICATION_JSON_UTF8.toString().contains(request.getContentType())) {
            response.getWriter().write(JSON.toJSONString(Response.OK("AuthSuccessful")));
        } else {
            super.onAuthenticationSuccess(request, response, authentication);
        }
    }
}
