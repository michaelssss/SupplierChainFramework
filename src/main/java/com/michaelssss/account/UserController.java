package com.michaelssss.account;

import com.michaelssss.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Api(value = "用户相关")
@RequestMapping(value = "User")
public class UserController {
    private UserCatalog userCatalog;

    @Autowired
    public UserController(UserCatalog userCatalog) {
        this.userCatalog = userCatalog;
    }

    @RequestMapping("Functions/get")
    @ResponseBody
    @ApiOperation(value = "获取功能列表")
    public Response getFunctions(@SessionAttribute User user) {
        return Response.OK(user.getHasAuthorityFunctionName());
    }

    @RequestMapping("Profile/get")
    @ResponseBody
    @ApiOperation(value = "获取用户信息")
    public Response getProfile(@SessionAttribute User user) {
        return Response.OK(user.getProfile());
    }

    @RequestMapping(value = "login")
    @ResponseBody
    @ApiOperation(value = "登陆")
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
