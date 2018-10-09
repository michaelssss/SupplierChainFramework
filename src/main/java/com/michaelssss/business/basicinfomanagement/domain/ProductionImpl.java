package com.michaelssss.business.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.basicinfomanagement.Production;
import com.michaelssss.business.basicinfomanagement.respository.ProductionRepository;
import com.michaelssss.utils.BusinessCodeGenerator;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;

/**
 * Description: @Author: @Date 2018/7/9
 */
@Builder
@Entity
@Data
@Table(name = "production")
public class ProductionImpl implements Production, Serializable {

  private static final long serialVersionUID = -962271783963574133L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(value = "业务无关id", readOnly = true, hidden = true)
  private Long id;

  @ApiModelProperty(value = "sku", example = "testSku")
  private String sku;

  @ApiModelProperty(value = "商品名称", example = "测试商品")
  private String name;

  @ApiModelProperty(value = "商品描述", example = "测试用")
  private String description;

  @ApiModelProperty(value = "品牌", example = "M")
  private String brands;

  @ApiModelProperty(value = "型号", example = "T")
  private String model;

  @ApiModelProperty(value = "标准", example = "tt")
  private String standard;

  @ApiModelProperty(value = "生效时间", example = "2018-09-14T03:21:03.000Z")
  private Date startTime;

  @ApiModelProperty(value = "失效时间", example = "2018-09-14T03:21:03.000Z")
  private Date endTime;

  @ApiModelProperty(value = "备注")
  private String remark;

  @ApiModelProperty(value = "状态", readOnly = true)
  private String state;

  @ApiModelProperty(value = "生产厂商名称", example = "testProducer")
  private String industry;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private Set<PropertyKeyValue> propertyKeyValues;

  @Override
  public void addProperty(PropertyKeyValue propertyKeyValue) {
    this.propertyKeyValues.add(propertyKeyValue);
  }

  @Override
  public Collection<PropertyKeyValue> getProperties() {
    return Collections.unmodifiableSet(this.propertyKeyValues);
  }

  @Override
  public void saveInfo() {
    this.sku = getPartNumberNo();
    SpringContextHolder.getBean(ProductionRepository.class).saveAndFlush(this);
  }

  @Override
  public void updateInfo() {
    SpringContextHolder.getBean(ProductionRepository.class).saveAndFlush(this);
  }

  private String getPartNumberNo() {
    return SpringContextHolder.getBean(BusinessCodeGenerator.class)
        .getSequence(this.getClass(), "SP");
  }
}
