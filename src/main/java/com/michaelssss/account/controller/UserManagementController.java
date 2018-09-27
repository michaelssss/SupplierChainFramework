package com.michaelssss.account.controller;

import com.michaelssss.account.*;
import com.michaelssss.base.Response;
import com.michaelssss.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.List;

@RestController
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
    public Response addUser(@RequestBody AddUserDataBinder map) {
        String username = map.getUsername();
        String password = map.getPassword();
        UserProfile userProfile = new UserProfile();
        String name = map.getName();
        String age = map.getAge();
        String sexual = map.getSexual();
        String phone = map.getPhone();
        String email = map.getEmail();
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
    public Response updatePassword(@SessionAttribute("user") User user,
                                   @RequestBody UpdatePasswordDataBinder map) {
        String password = map.getPassword();
        user.updatePassword(password);
        return Response.OK("修改密码成功");
    }

    @RequestMapping(value = "authority", method = RequestMethod.POST)
    @ApiOperation(value = "授权", tags = "用户管理")
    @ResponseBody
    public Response authority(@SessionAttribute("user") User user,
                              @RequestBody AuthorityDataBinder map) {
        String functionName = map.getFunctionName();
        FunctionName functionName1 = new FunctionName();
        functionName1.setFunctionName(functionName);
        functionName1 = functionNameCatalog.findOne(Example.of(functionName1)).get();
        String authUserName = map.getAuthUserName();
        User user1 = UserImpl.builder().username(authUserName).build();
        user1 = userCatalog.findOne(Example.of((UserImpl) user1)).get();
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
    public Response createGroup(@RequestBody GroupListDataBinder map) {
        GroupImpl group = new GroupImpl();
        group.setName(map.getGroupName());
        groupCatalog.saveAndFlush(group);
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
    public Response authorityGroup(@RequestBody JoinGroupDataBinder map) {
        String groupName = map.getGroupName();
        String username = map.getUsername();
        User user = UserImpl.builder().username(username).build();
        user = this.userCatalog.findOne(Example.of((UserImpl) user)).get();
        GroupImpl group = new GroupImpl();
        group.setName(groupName);
        group = groupCatalog.findOne(Example.of(group)).get();
        group.addUser(user);
        return Response.OK("已加入");
    }

    @RequestMapping(value = "Group/Users/list", method = RequestMethod.POST)
    @ApiOperation(value = "列出部门所有员工", tags = "用户管理")
    @ResponseBody
    public Response<List<User>> listUsersInGroup(@RequestBody GroupListDataBinder map) {
        String groupName = map.getGroupName();
        GroupImpl group = new GroupImpl();
        group.setName(groupName);
        group = groupCatalog.findOne(Example.of(group)).get();
        return (Response<List<User>>) Response.OK(group.getUsers());
    }

    @RequestMapping(value = "Users/list", method = RequestMethod.POST)
    @ApiOperation(value = "列出所有用户", tags = "用户管理")
    @ResponseBody
    public Response<List<User>> listUsers(@RequestBody UserListDataBinder userListDataBinder, HttpServletRequest request, HttpServletResponse response) {
        Pageable pageable = PageUtils.getPageableFromRequest(request);
        UserImpl sample = UserImpl.builder().username(userListDataBinder.getUsername()).build();
        Page<UserImpl> page = userCatalog.findAll(Example.of(sample), pageable);
        PageUtils.writeResponsePageHeader(page, response);
        return (Response<List<User>>) Response.OK(page.getContent());
    }
}
