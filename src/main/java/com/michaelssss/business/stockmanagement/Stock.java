package com.michaelssss.business.stockmanagement;

/**
 * Stock代表着一个委托商在本系统内的一个项目中仓库的信息
 * 故而，一个Stock与项目一一对应，但相同项目下，可以有多个收发货单
 */
public interface Stock {
    /**
     * 根据采购订单，添加收货单
     */
    void ReceivedCargoOrderAdd(ReceivedOrder receivedOrder);

    /**
     * 根据销售订单，添加出货单
     */
    void DeliveryCargoOrderAdd(DeliveryOrder deliveryOrder);
}
