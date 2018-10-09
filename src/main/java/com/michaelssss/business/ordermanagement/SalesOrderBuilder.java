package com.michaelssss.business.ordermanagement;

import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

public final class SalesOrderBuilder {

  private SalesOrderImpl salesOrderImpl;

  private SalesOrderBuilder() {
    salesOrderImpl = new SalesOrderImpl();
  }

  public static SalesOrderBuilder aSalesOrderImpl() {
    return new SalesOrderBuilder();
  }

  public SalesOrderBuilder entrustedCode(String entrustedCode) {
    salesOrderImpl.setEntrustedCode(entrustedCode);
    return this;
  }

  public SalesOrderBuilder buyer(String buyer) {
    salesOrderImpl.setBuyer(buyer);
    return this;
  }

  public SalesOrderBuilder paymentType(String paymentType) {
    salesOrderImpl.setPaymentType(paymentType);
    return this;
  }

  public SalesOrderBuilder currency(String currency) {
    salesOrderImpl.setCurrency(currency);
    return this;
  }

  public SalesOrderBuilder source(String source) {
    salesOrderImpl.setSource(source);
    return this;
  }

  public SalesOrderBuilder orderDate(Date orderDate) {
    salesOrderImpl.setOrderDate(orderDate);
    return this;
  }

  public SalesOrderBuilder serviceCharge(BigDecimal serviceCharge) {
    salesOrderImpl.setServiceCharge(serviceCharge);
    return this;
  }

  public SalesOrderBuilder prePayTotal(BigDecimal prePayTotal) {
    salesOrderImpl.setPrePayTotal(prePayTotal);
    return this;
  }

  public SalesOrderBuilder guaranteeMoneyOffset(BigDecimal guaranteeMoneyOffset) {
    salesOrderImpl.setGuaranteeMoneyOffset(guaranteeMoneyOffset);
    return this;
  }

  public SalesOrderBuilder actualPayTotal(BigDecimal actualPayTotal) {
    salesOrderImpl.setActualPayTotal(actualPayTotal);
    return this;
  }

  public SalesOrderBuilder goods(Collection<OrderGood> goods) {
    salesOrderImpl.setGoods(goods);
    return this;
  }

  public SalesOrderBuilder payChannel(String payChannel) {
    salesOrderImpl.setPayChannel(payChannel);
    return this;
  }

  public SalesOrderImpl build() {
    salesOrderImpl.setCode(salesOrderImpl.generateCode());
    return salesOrderImpl;
  }
}
