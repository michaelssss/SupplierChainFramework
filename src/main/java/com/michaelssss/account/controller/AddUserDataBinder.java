package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AddUserDataBinder {
    @ApiModelProperty(value = "账号名")
    private String username;
    @ApiModelProperty(value = "密码")
    private String password;
    @ApiModelProperty(value = "名字")
    private String name;
    @ApiModelProperty(value = "性别")
    private String sexual;
    @ApiModelProperty(value = "年龄")
    private String age;
    @ApiModelProperty(value = "手机号")
    private String phone;
    @ApiModelProperty(value = "邮箱地址")
    private String email;
}
