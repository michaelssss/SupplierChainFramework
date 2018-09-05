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

import java.util.Map;

@Controller
@RequestMapping(value = "UserManagement")
@Api(value = "用户管理")
public class UserManagementController {

    private UserCatalog userCatalog;
    private FunctionNameCatalog functionNameCatalog;
    private AuthSetCatalog authSetCatalog;

    @Autowired
    public UserManagementController(UserCatalog userCatalog, FunctionNameCatalog functionNameCatalog, AuthSetCatalog authSetCatalog) {
        this.userCatalog = userCatalog;
        this.functionNameCatalog = functionNameCatalog;
        this.authSetCatalog = authSetCatalog;
    }

    @RequestMapping(value = "User/add")
    @ApiOperation(value = "新增")
    @ResponseBody
    public Response addUser(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        User user = UserImpl.builder()
                .username(username)
                .password(password)
                .build();
        user.registered();
        return Response.OK("新增用户成功");
    }

    @RequestMapping(value = "User/Password/update")
    @ApiOperation(value = "修改密码")
    @ResponseBody
    public Response updatePassword(@SessionAttribute("user") User user, @RequestBody Map<String, String> map) {
        String password = map.get("password");
        user.updatePassword(password);
        return Response.OK("修改密码成功");
    }

    @RequestMapping(value = "authority")
    @ApiOperation(value = "授权")
    @ResponseBody
    public Response authority(@SessionAttribute("user") User user, @RequestBody Map<String, String> map) {
        String functionName = map.get("functionName");
        FunctionName functionName1 = new FunctionName();
        functionName1.setFunctionName(functionName);
        functionName1 = functionNameCatalog.findOne(Example.of(functionName1));
        String authUserName = map.get("authUserName");
        User user1 = UserImpl.builder().username(authUserName).build();
        user1 = userCatalog.findOne(Example.of((UserImpl) user1));
        try {
            user.authority(user1, functionName1);
        } catch (AuthorityException e) {
            return Response.NonOK(e.getMessage());
        }
        return Response.OK("授权成功");
    }

    @RequestMapping(value = "Group/create")
    @ApiOperation(value = "创建部门")
    @ResponseBody
    public Response createGroup(@RequestBody Map<String, String> map) {
        String groupName = map.get("groupName");
        Group group = new GroupImpl();
        group.setGroupName(groupName);
        authSetCatalog.saveAndFlush((GroupImpl) group);
        return Response.OK("创建部门成功");
    }

    @RequestMapping(value = "Group/join")
    @ApiOperation(value = "加入部门")
    @ResponseBody
    public Response authorityGroup(@SessionAttribute("user") User user, @RequestBody Map<String, Object> map) {
        String groupName = map.get("groupName").toString();
        String[] functionName = (String[]) map.get("functionNames");
        GroupImpl group = new GroupImpl();
        group.setGroupName(groupName);
        group = authSetCatalog.findOne(Example.of(group));
        group.addUser(user);
        return Response.OK("已加入");
    }
}
