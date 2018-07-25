package com.jzqh.account;

import com.jzqh.utils.JSON;
import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Map;

/**
 * 本类用于JSON对象登陆处理
 * 除了基本的FORM或者其他通用标准的登陆
 * 可以做到自定义登陆
 * 定义外部传入的JSON对象结构应该如下
 * <pre>
 *     {
 *         "username":"yourname",
 *         "password":"encodepassword"
 *     }
 * </pre>
 *
 * @author Michaelssss
 */
public class JSONUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private static Logger logger = LoggerFactory.getLogger(JSONUsernamePasswordAuthenticationFilter.class);

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (null != request.getContentType() && MediaType.APPLICATION_JSON_UTF8.toString().contains(request.getContentType())) {
            try {
                InputStream in = request.getInputStream();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                IOUtils.copy(in, out);
                Map<String, Object> jsonObject = JSON.parseJSONString2Map(new String(out.toByteArray(), Charset.forName("UTF-8")));
                UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                        jsonObject.get("username"), jsonObject.get("password"));
                setDetails(request, authRequest);

                return this.getAuthenticationManager().authenticate(authRequest);
            } catch (IOException | RuntimeException e) {
                logger.debug("", e);
                throw new BadCredentialsException(e.getLocalizedMessage());
            }
        } else return super.attemptAuthentication(request, response);
    }
}
