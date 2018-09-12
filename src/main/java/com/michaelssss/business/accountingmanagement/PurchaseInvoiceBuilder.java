package com.michaelssss.business.accountingmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

public final class PurchaseInvoiceBuilder {
    private PurchaseInvoiceImpl paymentVoucherImpl;

    private PurchaseInvoiceBuilder() {
        paymentVoucherImpl = new PurchaseInvoiceImpl();
    }

    public static PurchaseInvoiceBuilder aPaymentVoucherImpl() {
        return new PurchaseInvoiceBuilder();
    }

    public PurchaseInvoiceBuilder type(String type) {
        paymentVoucherImpl.setType(type);
        return this;
    }

    public PurchaseInvoiceBuilder purchaseCode(String purchaseCode) {
        paymentVoucherImpl.setPurchaseCode(purchaseCode);
        return this;
    }

    public PurchaseInvoiceBuilder amount(BigDecimal amount) {
        paymentVoucherImpl.setAmount(amount);
        return this;
    }

    public PurchaseInvoiceBuilder paymentMethod(String paymentMethod) {
        paymentVoucherImpl.setPaymentMethod(paymentMethod);
        return this;
    }

    public PurchaseInvoiceBuilder transferFinishDate(Date transferFinishDate) {
        paymentVoucherImpl.setTransferFinishDate(transferFinishDate);
        return this;
    }

    public PurchaseInvoiceBuilder receipts(SortedSet<TransferReceipt> receipts) {
        paymentVoucherImpl.setReceipts(receipts);
        return this;
    }

    public PurchaseInvoiceImpl build() {
        return paymentVoucherImpl;
    }
}
