package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.PropertyKeyValue;

import java.util.Collection;

/**
 * @Description:商品信息维护
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Production {

    /**
     * 保存商品信息
     */
    void saveInfo();

    /**
     * 添加属性及名称
     *
     * @param propertyKeyValue
     */
    void addProperty(PropertyKeyValue propertyKeyValue);

    Collection<PropertyKeyValue> getProperties();

    /**
     * 修改商品信息
     */
    void updateInfo();

}

