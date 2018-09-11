package com.michaelssss.rzzl2.basicinfomanagement.controller;

import com.michaelssss.rzzl2.basicinfomanagement.domain.ProductionClassification;
import com.michaelssss.rzzl2.basicinfomanagement.domain.TemplateProperty;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductionClassificationDataBinder {
    @ApiModelProperty(value = "需要绑定的父分类")
    private Long parentId;
    @ApiModelProperty(value = "分类唯一标识")
    private String code;
    @ApiModelProperty(value = "分类名称")
    private String name;
    @ApiModelProperty(value = "该分类需要填写的属性名称")
    private Set<TemplateProperty> templateProperties;
    @ApiModelProperty(value = "子分类")
    private List<ProductionClassification> child;
}
