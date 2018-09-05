package com.michaelssss.rzzl2.ordermanagement;

import java.math.BigDecimal;
import java.util.Date;

final class EntrustedOrderBuilder {
    private EntrustedOrderImpl entrustedOrderImpl;

    private EntrustedOrderBuilder() {
        entrustedOrderImpl = new EntrustedOrderImpl();
    }

    static EntrustedOrderBuilder anEntrustedOrderImpl() {
        return new EntrustedOrderBuilder();
    }

    EntrustedOrderBuilder entrustedCompany(String entrustedCompany) {
        entrustedOrderImpl.setEntrustedCompany(entrustedCompany);
        return this;
    }

    EntrustedOrderBuilder mainContractId(String mainContractId) {
        entrustedOrderImpl.setMainContractId(mainContractId);
        return this;
    }

    EntrustedOrderBuilder mainContractName(String mainContractName) {
        entrustedOrderImpl.setMainContractName(mainContractName);
        return this;
    }


    EntrustedOrderBuilder supplierName(String supplierName) {
        entrustedOrderImpl.setSupplierName(supplierName);
        return this;
    }

    EntrustedOrderBuilder orderDate(Date orderDate) {
        entrustedOrderImpl.setOrderDate(orderDate);
        return this;
    }

    EntrustedOrderBuilder paymentDate(String paymentDate) {
        entrustedOrderImpl.setPaymentDate(paymentDate);
        return this;
    }

    EntrustedOrderBuilder paymentMoney(BigDecimal paymentMoney) {
        entrustedOrderImpl.setPaymentMoney(paymentMoney);
        return this;
    }

    EntrustedOrderBuilder currency(String currency) {
        entrustedOrderImpl.setCurrency(currency);
        return this;
    }

    EntrustedOrderBuilder supplierContactName(String supplierContactName) {
        entrustedOrderImpl.setSupplierContactName(supplierContactName);
        return this;
    }

    EntrustedOrderBuilder supplierContactPhone(String supplierContactPhone) {
        entrustedOrderImpl.setSupplierContactPhone(supplierContactPhone);
        return this;
    }

    EntrustedOrderBuilder deliveryMethod(String deliveryMethod) {
        entrustedOrderImpl.setDeliveryMethod(deliveryMethod);
        return this;
    }

    EntrustedOrderBuilder pickDate(Date pickDate) {
        entrustedOrderImpl.setPickDate(pickDate);
        return this;
    }

    EntrustedOrderBuilder deliveryCostBearer(String deliveryCostBearer) {
        entrustedOrderImpl.setDeliveryCostBearer(deliveryCostBearer);
        return this;
    }

    EntrustedOrderBuilder receivedInfo(ReceivedInfo receivedInfo) {
        entrustedOrderImpl.setReceivedInfo(receivedInfo);
        return this;
    }

    EntrustedOrderImpl build() {
        entrustedOrderImpl.setCode(entrustedOrderImpl.generateCode());
        return entrustedOrderImpl;
    }
}
