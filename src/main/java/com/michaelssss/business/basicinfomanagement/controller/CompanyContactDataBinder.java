package com.michaelssss.business.basicinfomanagement.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyContactDataBinder {
    private String companyName;
    private String historyId;
    @ApiModelProperty(value = "联系人类型", example = "唯一联系人")
    private String contactType;
    @ApiModelProperty(value = "联系人姓名", example = "Michaelssss")
    private String name;
    @ApiModelProperty(value = "手机号码", example = "13800000000")
    private String mobilePhone;
    @ApiModelProperty(value = "固话", example = "028-12345678")
    private String phone;
    @ApiModelProperty(value = "传真", example = "")
    private String fax;
    @ApiModelProperty(value = "邮件", example = "test@test.com")
    private String email;
    @ApiModelProperty(value = "联系人所属部门", example = "挖矿部")
    private String department;
    @ApiModelProperty(value = "职位", example = "CEO")
    private String position;
    @ApiModelProperty(value = "是否默认", allowableValues = "true,false")
    private String isDefault;
    @ApiModelProperty(value = "备注", example = "")
    private String remark;
}
