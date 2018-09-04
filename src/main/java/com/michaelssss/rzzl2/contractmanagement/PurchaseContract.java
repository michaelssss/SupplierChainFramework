package com.michaelssss.rzzl2.contractmanagement;

/**
 * 采购合同根据采购订单生成
 */
public interface PurchaseContract {
    String Confirm = "Confirm";
    String Terminate = "Terminate";

    /***
     * 添加采购合同
     *
     */
    void addPurchaseContract();

    /**
     * 修改采购合同
     * exception：不能修改异常
     */
    void updatePurchaseContract();

    /***
     *删除采购合同
     */
    void deletePurchaseContract();

    /**
     * 合同生效
     */
    void confirmPurchaseContract();

    String getPurchaseContractNo();
}
