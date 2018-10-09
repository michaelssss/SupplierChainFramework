package com.michaelssss.business.ordermanagement;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * 订单通用接口
 */
public interface Order {

  String NEW = "NEW";
  String CONFIRM = "CONFIRM";
  String TERMINATE = "TERMINATE";
  String DELIVERY_CONFIRM = "DELIVERY_CONFIRM";
  String RECEIVED_CONFIRM = "RECEIVED_CONFIRM";

  /**
   * 获取订单编号
   */
  String getCode();

  /**
   * 获取订单状态
   */
  String getStatus();

  /**
   * 获取商品列表
   */
  Collection<OrderGood> getGoods();

  /**
   * 获取当前订单货品总价
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
