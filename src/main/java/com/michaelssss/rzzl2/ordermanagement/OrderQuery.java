package com.michaelssss.rzzl2.ordermanagement;

public interface OrderQuery {
    /**
     * 根据委托单编号查询委托单对象
     *
     * @param entrustedCode 委托单编号
     * @return 若委托单存在则返回对象，否则返回null
     */
    EntrustedOrder queryEntrustedOrderByEntrustedCode(String entrustedCode);

    /**
     * 根据销售订单编号查询委托单对象
     *
     * @param salesCode 销售订单编号
     * @return 若委托单存在则返回对象，否则返回null
     */
    EntrustedOrder queryEntrustedOrderBySalesCode(String salesCode);

    /**
     * 根据采购订单编号查询委托单对象
     *
     * @param purchaseCode 采购订单编号
     * @return 若委托单存在则返回对象，否则返回null
     */
    EntrustedOrder queryEntrustedOrderByPurchaseCode(String purchaseCode);
}
