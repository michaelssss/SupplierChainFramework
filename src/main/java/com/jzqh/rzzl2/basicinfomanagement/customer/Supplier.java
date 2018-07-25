package com.jzqh.rzzl2.basicinfomanagement.customer;

/**
 * @Description:合作伙伴基础信息仓储商信息
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Supplier {

    /**
     * 添加供应商
     */
    void addInfo();

    /**
     * 修改供应商
     */
    void updateInfo();

    /**
     * 删除供应商
     */
    void deleteInfo();

    /**
     * 准入
     */
    void requestPermit();
}
