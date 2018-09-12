package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.ProductionImpl;

import java.util.List;
import java.util.Map;

public interface ProductionClassification {
    /**
     * 往该分类放置商品
     *
     * @param production 商品信息
     * @param keyValue   商品分类属性Map
     */
    void addProduction(Production production, Map<String, String> keyValue);

    /**
     * 获取该分类及其子分类的所有商品
     *
     * @return
     */
    List<ProductionImpl> productionInThisClassification();

    void addSubClassification(ProductionClassification productionClassification);

    void save();
}
