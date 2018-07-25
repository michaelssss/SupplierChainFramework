package com.jzqh.rzzl2.accountingmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

final class ReceiptVoucherBuilder {
    private ReceiptVoucherImpl receiptVoucherImpl;

    private ReceiptVoucherBuilder() {
        receiptVoucherImpl = new ReceiptVoucherImpl();
    }

    public static ReceiptVoucherBuilder aReceiptVoucherImpl() {
        return new ReceiptVoucherBuilder();
    }

    public ReceiptVoucherBuilder type(String type) {
        receiptVoucherImpl.setType(type);
        return this;
    }

    public ReceiptVoucherBuilder salesOrderCode(String salesOrderCode) {
        receiptVoucherImpl.setSalesOrderCode(salesOrderCode);
        return this;
    }

    public ReceiptVoucherBuilder amount(BigDecimal amount) {
        receiptVoucherImpl.setAmount(amount);
        return this;
    }

    public ReceiptVoucherBuilder paymentMethod(String paymentMethod) {
        receiptVoucherImpl.setPaymentMethod(paymentMethod);
        return this;
    }

    public ReceiptVoucherBuilder transferFinishDate(Date transferFinishDate) {
        receiptVoucherImpl.setTransferFinishDate(transferFinishDate);
        return this;
    }

    public ReceiptVoucherBuilder receipts(SortedSet<TransferReceipt> receipts) {
        receiptVoucherImpl.setReceipts(receipts);
        return this;
    }

    public ReceiptVoucherImpl build() {
        return receiptVoucherImpl;
    }
}
