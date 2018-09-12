package com.michaelssss.business.basicinfomanagement.domain;

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
@Table(name = "company_shareholder_info")
public class ShareholderInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(hidden = true)
    private Long id;
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
