package com.michaelssss.business.basicinfomanagement.domain;

import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 商品所在分类需要填写的属性及值
 */
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "production_property_key_value")
public class PropertyKeyValue implements Serializable {

  private static final long serialVersionUID = -77569692436381348L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "uid", readOnly = true, hidden = true)
  private Long uid;

  @ApiModelProperty(value = "属性名")
  @Column(length = 64, name = "`key`")
  private String key;

  @ApiModelProperty(value = "属性值")
  @Column(length = 64, name = "`value`")
  private String value;
}
