package com.michaelssss.business.basicinfomanagement.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyBankAccountDataBinder {

  private String companyName;
  private String historyId;

  @ApiModelProperty(value = "账户名称", example = "收款")
  private String accountName;

  @ApiModelProperty(value = "银行账号", example = "1234567890")
  private String bankAccount;

  @ApiModelProperty(value = "开户行名称", example = "中国建设银行")
  private String bankName;

  @ApiModelProperty(value = "开户行全称", example = "中国健身银行深圳市南山支行")
  private String bankFullName;

  @ApiModelProperty(value = "账号状态", example = "可用")
  private String status;

  @ApiModelProperty(value = "币种", example = "人民币")
  private String currency;

  @ApiModelProperty(value = "是否默认", allowableValues = "true,false")
  private String isDefault;

  @ApiModelProperty(value = "备注")
  private String remark;
}
