package com.michaelssss.rzzl2.accountingmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

public final class PaymentVoucherBuilder {
    private PaymentVoucherImpl paymentVoucherImpl;

    private PaymentVoucherBuilder() {
        paymentVoucherImpl = new PaymentVoucherImpl();
    }

    public static PaymentVoucherBuilder aPaymentVoucherImpl() {
        return new PaymentVoucherBuilder();
    }

    public PaymentVoucherBuilder type(String type) {
        paymentVoucherImpl.setType(type);
        return this;
    }

    public PaymentVoucherBuilder purchaseCode(String purchaseCode) {
        paymentVoucherImpl.setPurchaseCode(purchaseCode);
        return this;
    }

    public PaymentVoucherBuilder amount(BigDecimal amount) {
        paymentVoucherImpl.setAmount(amount);
        return this;
    }

    public PaymentVoucherBuilder paymentMethod(String paymentMethod) {
        paymentVoucherImpl.setPaymentMethod(paymentMethod);
        return this;
    }

    public PaymentVoucherBuilder transferFinishDate(Date transferFinishDate) {
        paymentVoucherImpl.setTransferFinishDate(transferFinishDate);
        return this;
    }

    public PaymentVoucherBuilder receipts(SortedSet<TransferReceipt> receipts) {
        paymentVoucherImpl.setReceipts(receipts);
        return this;
    }

    public PaymentVoucherImpl build() {
        return paymentVoucherImpl;
    }
}
