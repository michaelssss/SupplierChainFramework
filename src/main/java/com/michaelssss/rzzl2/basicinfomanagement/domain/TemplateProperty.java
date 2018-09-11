package com.michaelssss.rzzl2.basicinfomanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.*;

/**
 * 表示分类需要填写的属性名称
 */
@Table(name = "template_property")
@Entity
@Data
public class TemplateProperty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "无关ID", readOnly = true, hidden = true)
    private Long uid;
    @ApiModelProperty(value = "属性名称")
    private String propertyName;
}
