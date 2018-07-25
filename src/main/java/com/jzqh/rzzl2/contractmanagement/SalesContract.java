package com.jzqh.rzzl2.contractmanagement;

public interface SalesContract {
    String Confirm = "Confirm";
    String Terminate = "Terminate";

    /***
     * 添加销售合同
     *
     */
    void addSalesContract();

    /**
     * 修改销售合同
     * exception：不能修改异常
     */
    void updateSalesContract();

    /***
     *删除销售合同
     */
    void deleteSalesContract();

    /**
     * 合同生效
     */
    void confirmSalesContract();

    String getSalesContract();
}
