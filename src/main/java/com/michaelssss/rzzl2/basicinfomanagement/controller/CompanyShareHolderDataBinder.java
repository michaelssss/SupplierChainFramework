package com.michaelssss.rzzl2.basicinfomanagement.controller;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CompanyShareHolderDataBinder {
    private String companyName;
    private String historyId;
    @ApiModelProperty(value = "股东姓名", example = "Michaelssss")
    private String shareholderName;
    @ApiModelProperty(value = "持股比例", example = "100%")
    private String fundedOfRatio;
    @ApiModelProperty(value = "")
    private String contribution;
    @ApiModelProperty(value = "币种", example = "人民币")
    private String currency;
    @ApiModelProperty(value = "投资类型", example = "股权")
    private String investmentOfType;
    @ApiModelProperty(value = "备注", example = "")
    private String remark;

}
