package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class UpdatePasswordDataBinder {

  @ApiModelProperty(value = "需要修改的密码")
  private String password;
}
