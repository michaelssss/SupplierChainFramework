package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.PropertyKeyValue;
import java.util.Collection;

/**
 * @Description:商品信息维护 @Author:tanshaoxing @Date:2018/7/11
 */
public interface Production {

  /**
   * 保存商品信息
   */
  void saveInfo();

  /**
   * 添加属性及名称
   */
  void addProperty(PropertyKeyValue propertyKeyValue);

  Collection<PropertyKeyValue> getProperties();

  /**
   * 修改商品信息
   */
  void updateInfo();

  /**
   * 获取商品Sku
   */
  String getSku();

  /**
   * 获取商品名称
   */
  String getName();

  /**
   * 获取商品描述
   */
  String getDescription();

  /**
   * 获取商品品牌
   */
  String getBrands();

  /**
   * 获取商品型号
   */
  String getModel();

  String getStandard();

  /**
   * 商品备注
   */
  String getRemark();

  /**
   * 获取商品生产企业
   */
  String getIndustry();
}
