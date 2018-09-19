package com.michaelssss.business.basicinfomanagement.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyAddrDataBinder {
    private String companyName;
    private String historyId;
    @ApiModelProperty(value = "地址类型", example = "国内")
    private String addressType;
    @ApiModelProperty(value = "省份", example = "广东")
    private String province;
    @ApiModelProperty(value = "城市", example = "深圳")
    private String city;
    @ApiModelProperty(value = "区域", example = "南山区")
    private String area;
    @ApiModelProperty(value = "详情", example = "广东深圳南山区哈哈哈哈哈")
    private String detail;
    @ApiModelProperty(value = "邮政编码", example = "543002")
    private String zipCode;//邮政编码
    @ApiModelProperty(value = "备注")
    private String remark;
    @ApiModelProperty(value = "是否默认", allowableValues = "true,false")
    private String isDefault;
}
