package com.michaelssss.business.basicinfomanagement.controller;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanySearchConditionBinder implements Serializable {

  @ApiModelProperty(value = "公司名称")
  private String companyName;

  @ApiModelProperty(value = "公司类型")
  private String companyType;
}
