package com.michaelssss.rzzl2.ordermanagement;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 订单通用接口
 */
public interface Order {
    String New = "New";
    String Confirm = "CONFIRM";
    String Terminate = "TERMINATE";
    String DeliveryConfirm = "DeliveryConfirm";
    String ReceivedConfirm = "ReceivedConfirm";

    /**
     * 获取订单编号
     *
     * @return
     */
    String getCode();

    /**
     * 获取订单状态
     *
     * @return
     */
    String getStatus();

    /**
     * 获取商品列表
     *
     * @return
     */
    Collection<OrderGood> getGoods();

    /**
     * 获取当前订单货品总价
     *
     * @return
     */
    BigDecimal getOrderGoodTotalPrice();

    /**
     * 委托单生效
     */
    void confirm();

    /**
     * 委托单终止
     */
    void terminate();

    /**
     * 往未生效订单中添加商品
     *
     * @param good 商品信息
     * @throws RuntimeException 如果订单已经生效了
     */
    void addOrderGood(OrderGood good);

    /**
     * 从未生效商品列表中移除商品信息
     *
     * @param good 商品信息
     * @throws RuntimeException 如果订单已经生效了
     */
    void removeOrderGood(OrderGood good);
}
