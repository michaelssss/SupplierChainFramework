package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.PurchaseClientApply;

/**
 * @Description:合作伙伴基础信息资金方信息
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Purchaser {

    /**
     * 添加采购方信息
     */
    void apply(PurchaseClientApply purchaseClientApply, Company company);

    /**
     * 获取公司信息
     *
     * @return 当前绑定的公司信息
     */
    Company getCompany();
}
