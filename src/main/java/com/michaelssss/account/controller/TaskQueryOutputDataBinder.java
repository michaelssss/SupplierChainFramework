package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TaskQueryOutputDataBinder {

  @ApiModelProperty(value = "任务名称")
  private String taskName;

  @ApiModelProperty(value = "任务Id")
  private String taskId;
}
