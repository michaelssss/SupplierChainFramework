package com.michaelssss.business.basicinfomanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_bank_account")
public class BankAccount implements Serializable {

  private static final long serialVersionUID = -3634630176993558857L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long id;

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
