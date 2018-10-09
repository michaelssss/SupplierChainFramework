package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AuthorityDataBinder {

  @ApiModelProperty(value = "需要授予的权限名称，通过Functions/get获取")
  private String functionName;

  @ApiModelProperty(value = "需要授予的用户账号名")
  private String authUserName;
}
