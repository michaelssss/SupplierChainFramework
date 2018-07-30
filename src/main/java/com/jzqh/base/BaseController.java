package com.jzqh.base;

import com.jzqh.account.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequestMapping("Menu")
@Controller
@Slf4j
public class BaseController {


    @RequestMapping("get")
    @ResponseBody
    public Response getMenu(Principal principal) {
        UsernamePasswordAuthenticationToken token = extractUserToken(principal);
        return Response.OK(((User) token.getPrincipal()).getMenus());
    }


    private UsernamePasswordAuthenticationToken extractUserToken(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken)
            return (UsernamePasswordAuthenticationToken) principal;
        else throw new RuntimeException("not login");
    }
}
