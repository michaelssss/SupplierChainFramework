package com.michaelssss.account.controller;

import com.michaelssss.account.FunctionName;
import com.michaelssss.account.Token;
import com.michaelssss.account.User;
import com.michaelssss.account.UserCatalog;
import com.michaelssss.account.UserImpl;
import com.michaelssss.account.UserProfile;
import com.michaelssss.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Api(value = "用户相关", tags = "用户相关")
@RequestMapping(value = "User")
@SuppressWarnings("unchecked")
public class UserController {

  private UserCatalog userCatalog;

  @Autowired
  public UserController(UserCatalog userCatalog) {
    this.userCatalog = userCatalog;
  }

  @RequestMapping(value = "Functions/get", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "获取功能列表", tags = "用户相关")
  public Response<Set<FunctionName>> getFunctions(@SessionAttribute User user) {
    List<FunctionName> functionNames = new ArrayList<>(user.getHasAuthorityFunctionName());
    functionNames.sort((o1, o2) -> o1.getFunctionName().compareTo(o2.getFunctionName()));
    return (Response<Set<FunctionName>>) Response.OK(functionNames);
  }

  @RequestMapping(value = "Profile/get", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "获取用户信息", tags = "用户相关")
  public Response<UserProfile> getProfile(@SessionAttribute User user) {
    return (Response<UserProfile>) Response.OK(user.getProfile());
  }

  @RequestMapping(value = "login", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "登陆", tags = "用户相关")
  public Response login(@RequestBody Map<String, Object> requestMap) throws IOException {
    String username = (String) requestMap.get("username");
    UserImpl example = UserImpl.builder().username(username).build();
    Optional<UserImpl> user = userCatalog.findOne(Example.of(example));
    if (user.isPresent()) {
      Token token =
          user.get()
              .login(
                  (String) requestMap.get("password"),
                  new Date(Long.valueOf(requestMap.get("outdate").toString())));
      if (null != token) {
        return Response.OK(token);
      }
    }
    return Response.NonOK("username or password not validate");
  }

  @RequestMapping(value = "logout", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "登出", tags = "用户相关")
  public Response logout(@SessionAttribute("user") User user, @CookieValue("token") String token) {
    user.logout(token);
    return Response.OK("登出成功");
  }
}
