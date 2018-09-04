package com.michaelssss.rzzl2.ordermanagement;

import java.util.Collection;
import java.util.Date;

public final class PurchaseOrderBuilder {
    private PurchaseOrderImpl purchaseOrderImpl;

    private PurchaseOrderBuilder() {
        purchaseOrderImpl = new PurchaseOrderImpl();
    }

    public static PurchaseOrderBuilder aPurchaseOrderImpl() {
        return new PurchaseOrderBuilder();
    }

    public PurchaseOrderBuilder entrustedCode(String entrustedCode) {
        purchaseOrderImpl.setEntrustedCode(entrustedCode);
        return this;
    }

    public PurchaseOrderBuilder currency(String currency) {
        purchaseOrderImpl.setCurrency(currency);
        return this;
    }

    public PurchaseOrderBuilder deliveryDate(Date deliveryDate) {
        purchaseOrderImpl.setDeliveryDate(deliveryDate);
        return this;
    }

    public PurchaseOrderBuilder receivedInfo(ReceivedInfo receivedInfo) {
        purchaseOrderImpl.setReceivedInfo(receivedInfo);
        return this;
    }

    public PurchaseOrderBuilder orderDate(Date orderDate) {
        purchaseOrderImpl.setOrderDate(orderDate);
        return this;
    }

    public PurchaseOrderBuilder goods(Collection<OrderGood> goods) {
        purchaseOrderImpl.setGoods(goods);
        return this;
    }

    public PurchaseOrderImpl build() {
        purchaseOrderImpl.setCode(purchaseOrderImpl.generateCode());
        return purchaseOrderImpl;
    }
}
