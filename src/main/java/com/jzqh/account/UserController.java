package com.jzqh.account;

import com.jzqh.base.Response;
import com.jzqh.utils.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping(value = "User")
public class UserController {
    private UserCatalog userCatalog;
    private TokenCatalog tokenCatalog;

    @Autowired
    public UserController(UserCatalog userCatalog, TokenCatalog tokenCatalog) {
        this.userCatalog = userCatalog;
        this.tokenCatalog = tokenCatalog;
    }

    @RequestMapping(value = "login")
    public void login(@RequestBody Map<String, Object> requestMap, HttpServletResponse response) throws IOException {
        String username = (String) requestMap.get("username");
        UserImpl example = UserImpl.builder().username(username).build();
        UserImpl user = userCatalog.findOne(Example.of(example));
        if (null != user) {
            if (user.validatePassword((String) requestMap.get("password"))) {
                Token token = new Token();
                token.setToken(UUID.randomUUID().toString());
                token.setOutdate(new Date(Long.valueOf(requestMap.get("outdate").toString())));
                token.setUser(user);
                tokenCatalog.save(token);
                response.setStatus(HttpServletResponse.SC_OK);
                response.getWriter().write(JSON.toJSONString(token));
                return;
            }
        }
        response.setStatus(HttpServletResponse.SC_NOT_FOUND);
        response.getWriter().write("user not found");
    }

    @RequestMapping("Menu/get")
    @ResponseBody
    public Response getMenu(@SessionAttribute User user) {
        return Response.OK(user.getMenus());
    }

    @RequestMapping("Action/get")
    @ResponseBody
    public Response getActions(@SessionAttribute User user) {
        return Response.OK(user.getActions());
    }

    @RequestMapping("Profile/get")
    @ResponseBody
    public Response getProfile(@SessionAttribute User user) {
        return Response.OK(user.getProfile());
    }
}
