package com.michaelssss.business.accountingmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

final class SalesInvoiceBuilder {

  private SalesInvoiceImpl receiptVoucherImpl;

  private SalesInvoiceBuilder() {
    receiptVoucherImpl = new SalesInvoiceImpl();
  }

  public static SalesInvoiceBuilder aReceiptVoucherImpl() {
    return new SalesInvoiceBuilder();
  }

  public SalesInvoiceBuilder type(String type) {
    receiptVoucherImpl.setType(type);
    return this;
  }

  public SalesInvoiceBuilder salesOrderCode(String salesOrderCode) {
    receiptVoucherImpl.setSalesOrderCode(salesOrderCode);
    return this;
  }

  public SalesInvoiceBuilder amount(BigDecimal amount) {
    receiptVoucherImpl.setAmount(amount);
    return this;
  }

  public SalesInvoiceBuilder paymentMethod(String paymentMethod) {
    receiptVoucherImpl.setPaymentMethod(paymentMethod);
    return this;
  }

  public SalesInvoiceBuilder transferFinishDate(Date transferFinishDate) {
    receiptVoucherImpl.setTransferFinishDate(transferFinishDate);
    return this;
  }

  public SalesInvoiceBuilder receipts(SortedSet<TransferReceipt> receipts) {
    receiptVoucherImpl.setReceipts(receipts);
    return this;
  }

  public SalesInvoiceImpl build() {
    return receiptVoucherImpl;
  }
}
