package com.jzqh.rzzl2.basicinfomanagement.goods;

/**
 * @Description:商品信息维护
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface ProductionInfo {

    /**
     * 保存商品信息
     */
    void saveInfo();

    /**
     * 修改商品信息
     */
    void updateInfo();

    /**
     * 删除商品信息
     */
    void deleteInfo();

    String getPartNumberNo();
}

