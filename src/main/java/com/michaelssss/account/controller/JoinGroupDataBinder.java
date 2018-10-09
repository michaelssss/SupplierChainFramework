package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class JoinGroupDataBinder {

  @ApiModelProperty(value = "用户账户名")
  private String username;

  @ApiModelProperty(value = "群组名")
  private String groupName;
}
