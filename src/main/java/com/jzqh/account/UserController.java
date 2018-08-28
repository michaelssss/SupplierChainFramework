package com.jzqh.account;

import com.jzqh.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping(value = "User")
public class UserController {
    private UserCatalog userCatalog;

    @Autowired
    public UserController(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    @RequestMapping("Menu/get")
    @ResponseBody
    public Response getMenu(@SessionAttribute User user) {
        MenuBo menuBo = MenuUtil.buildTree(user);
        return Response.OK(menuBo);
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

    @RequestMapping(value = "login")
    @ResponseBody
    public Response login(@RequestBody Map<String, Object> requestMap) throws IOException {
        String username = (String) requestMap.get("username");
        UserImpl example = UserImpl.builder().username(username).build();
        UserImpl user = userCatalog.findOne(Example.of(example));
        if (null != user) {
            Token token = user.login((String) requestMap.get("password"), new Date(Long.valueOf(requestMap.get("outdate").toString())));
            if (null != token) {
                return Response.OK(token);
            }
        }
        return Response.NonOK("username or password not validate");
    }
}
