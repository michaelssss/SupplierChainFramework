package com.michaelssss.account;

import com.michaelssss.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping(value = "UserManagement")
@Api(value = "用户管理", tags = "用户管理")
public class UserManagementController {

    private UserCatalog userCatalog;
    private FunctionNameCatalog functionNameCatalog;
    private GroupCatalog groupCatalog;

    @Autowired
    public UserManagementController(UserCatalog userCatalog, FunctionNameCatalog functionNameCatalog, GroupCatalog groupCatalog) {
        this.userCatalog = userCatalog;
        this.functionNameCatalog = functionNameCatalog;
        this.groupCatalog = groupCatalog;
    }

    @RequestMapping(value = "User/add", method = RequestMethod.POST)
    @ApiOperation(value = "新增", tags = "用户管理")
    @ResponseBody
    public Response addUser(@RequestBody Map<String, String> map) {
        String username = map.get("username");
        String password = map.get("password");
        UserProfile userProfile = new UserProfile();
        String name = map.get("name");
        String age = map.get("age");
        String sexual = map.get("sexual");
        String phone = map.get("phone");
        String email = map.get("email");
        userProfile.setName(name);
        userProfile.setAge(age);
        userProfile.setSexual(sexual);
        userProfile.setPhone(phone);
        userProfile.setEmail(email);
        User user = UserImpl.builder()
                .username(username)
                .password(password)
                .functionNames(new HashSet<>())
                .userProfile(userProfile)
                .build();
        user.registered();
        return Response.OK("新增用户成功");
    }

    @RequestMapping(value = "User/Password/update", method = RequestMethod.POST)
    @ApiOperation(value = "修改密码", tags = "用户管理")
    @ResponseBody
    public Response updatePassword(@SessionAttribute("user") User user, @RequestBody Map<String, String> map) {
        String password = map.get("password");
        user.updatePassword(password);
        return Response.OK("修改密码成功");
    }

    @RequestMapping(value = "authority", method = RequestMethod.POST)
    @ApiOperation(value = "授权", tags = "用户管理")
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

    @RequestMapping(value = "Group/create", method = RequestMethod.POST)
    @ApiOperation(value = "创建部门", tags = "用户管理")
    @ResponseBody
    public Response createGroup(@RequestBody Map<String, String> map) {
        String groupName = map.get("groupName");
        Group group = new GroupImpl();
        group.setGroupName(groupName);
        groupCatalog.saveAndFlush((GroupImpl) group);
        return Response.OK("创建部门成功");
    }

    @RequestMapping(value = "Group/list", method = RequestMethod.POST)
    @ApiOperation(tags = "用户管理", value = "查询所有部门")
    @ResponseBody
    public Response<List<Group>> listGroup() {
        return (Response<List<Group>>) Response.OK(this.groupCatalog.findAll());
    }

    @RequestMapping(value = "Group/join", method = RequestMethod.POST)
    @ApiOperation(tags = "用户管理", value = "加入部门")
    @ResponseBody
    public Response authorityGroup(@RequestBody Map<String, Object> map) {
        String groupName = map.get("groupName").toString();
        String username = map.get("username").toString();
        User user = UserImpl.builder().username(username).build();
        user = this.userCatalog.findOne(Example.of((UserImpl) user));
        GroupImpl group = new GroupImpl();
        group.setGroupName(groupName);
        group = groupCatalog.findOne(Example.of(group));
        group.addUser(user);
        return Response.OK("已加入");
    }

    @RequestMapping(value = "Group/Users/list", method = RequestMethod.POST)
    @ApiOperation(value = "列出部门所有员工", tags = "用户管理")
    @ResponseBody
    public Response listUsersInGroup(@RequestBody Map<String, Object> map) {
        String groupName = map.get("groupName").toString();
        GroupImpl group = new GroupImpl();
        group.setGroupName(groupName);
        group = groupCatalog.findOne(Example.of(group));
        return Response.OK(group.getUsers());
    }

    @RequestMapping(value = "Users/list", method = RequestMethod.POST)
    @ApiOperation(value = "列出所有用户", tags = "用户管理")
    @ResponseBody
    public Response listUsers() {
        return Response.OK(userCatalog.findAll());
    }
}
