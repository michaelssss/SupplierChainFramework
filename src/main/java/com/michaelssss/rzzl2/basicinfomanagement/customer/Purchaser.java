package com.michaelssss.rzzl2.basicinfomanagement.customer;

import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplyPurchaseImpl;

/**
 * @Description:合作伙伴基础信息资金方信息
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Purchaser {

    /**
     * 添加采购商
     */
    void addInfo();

    /**
     * 修改采购商信息
     */
    void updateInfo();

    /**
     * 删除采购商
     */
    void deleteInfo();

    /**
     * 准入
     */
    void requestPermit();

    /***
     * 添加准入信息
     */
    void addPermitInfo(EntryApplyPurchaseImpl entryApplyPurchase);

}
