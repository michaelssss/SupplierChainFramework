package com.jzqh.base;

import com.jzqh.account.User;
import com.jzqh.account.accessmanagement.authority.Menu;
import com.jzqh.account.accessmanagement.authority.MenuCatalog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;

@RequestMapping("Menu")
@Controller
@Slf4j
public class BaseController {

    private MenuCatalog menuCatalog;

    @Autowired
    public BaseController(MenuCatalog menuCatalog) {
        this.menuCatalog = menuCatalog;
    }

    @RequestMapping("get")
    @ResponseBody
    public Response getMenu(Principal principal) {
        UsernamePasswordAuthenticationToken token = extractUserToken(principal);
        return Response.OK(((User) token.getPrincipal()).getRootMenu());
    }

    @RequestMapping("Action/get")
    @ResponseBody
    public Response getActions(@RequestBody Menu menu, Principal principal) {
        Example<Menu> example = Example.of(menu);
        Menu menu1 = menuCatalog.findOne(example);
        UsernamePasswordAuthenticationToken token = extractUserToken(principal);
        User user = (User) token.getPrincipal();
        return Response.OK(user.getActions(menu1));
    }

    private UsernamePasswordAuthenticationToken extractUserToken(Principal principal) {
        if (principal instanceof UsernamePasswordAuthenticationToken)
            return (UsernamePasswordAuthenticationToken) principal;
        else throw new RuntimeException("not login");
    }
}
