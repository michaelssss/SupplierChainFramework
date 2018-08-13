package com.jzqh.account;

import com.jzqh.base.Response;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "User")
public class UserController {

    @RequestMapping("Menu/get")
    @ResponseBody
    public Response getMenu() {
        return Response.OK(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getMenus());
    }

    @RequestMapping("Action/get")
    @ResponseBody
    public Response getActions() {
        return Response.OK(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getActions());
    }

    @RequestMapping("Profile/get")
    @ResponseBody
    public Response getProfile() {
        return Response.OK(((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getProfile());
    }
}
