package com.michaelssss.account.controller;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel
public class TaskDataBinder {

  @ApiModelProperty(name = "任务id")
  private String taskId;
}
