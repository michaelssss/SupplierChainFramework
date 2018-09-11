package com.michaelssss.rzzl2.basicinfomanagement.domain;


import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "company_address")
public class Address {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true,readOnly = true)
    private Long id;
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
    @ApiModelProperty(value = "联系人", example = "Michaelssss")
    private String connectPeople;
    @ApiModelProperty(value = "联系人手机", example = "13800000000")
    private String connectPhone;
    @ApiModelProperty(value = "联系人Email", example = "test@Test.com")
    private String connectEmail;
    @ApiModelProperty(value = "是否默认联系人", allowableValues = "true,false")
    private String isDefault;
    @ApiModelProperty(value = "备注")
    private String remark;
}
