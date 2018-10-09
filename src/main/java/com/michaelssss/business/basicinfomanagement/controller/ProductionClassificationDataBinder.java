package com.michaelssss.business.basicinfomanagement.controller;

import com.michaelssss.business.basicinfomanagement.domain.ProductionClassificationImpl;
import com.michaelssss.business.basicinfomanagement.domain.TemplateProperty;
import io.swagger.annotations.ApiModelProperty;
import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

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
  private List<ProductionClassificationImpl> child;
}
