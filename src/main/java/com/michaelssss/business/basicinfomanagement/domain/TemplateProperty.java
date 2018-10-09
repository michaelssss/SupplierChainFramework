package com.michaelssss.business.basicinfomanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 表示分类需要填写的属性名称
 */
@Table(name = "template_property")
@Entity
@Data
public class TemplateProperty implements Serializable {

  private static final long serialVersionUID = -6542104895558079287L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "无关ID", readOnly = true, hidden = true)
  private Long uid;

  @ApiModelProperty(value = "属性名称")
  private String propertyName;
}
