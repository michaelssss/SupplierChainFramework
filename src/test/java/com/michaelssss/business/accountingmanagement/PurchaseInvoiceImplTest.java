package com.michaelssss.business.accountingmanagement;

import com.michaelssss.SpringBootTestBasic;
import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

public class PurchaseInvoiceImplTest extends SpringBootTestBasic {

  @Autowired
  private PurchaseInvoiceCatalog repository;

  static PurchaseInvoiceImpl fakePaymentVoucher() {
    PurchaseInvoiceBuilder builder = PurchaseInvoiceBuilder.aPaymentVoucherImpl();
    builder
        .type("")
        .amount(BigDecimal.valueOf(2000))
        .purchaseCode("testCode")
        .paymentMethod("BankTransfer")
        .receipts(new TreeSet<>())
        .transferFinishDate(new Date());
    return builder.build();
  }

  @Test
  public void addPaymentVoucher() {
    PurchaseInvoiceImpl paymentVoucher = repository.saveAndFlush(fakePaymentVoucher());
    PurchaseInvoiceImpl sample = new PurchaseInvoiceImpl();
    sample.setPurchaseCode("testCode");
    Assert.assertEquals(1L, this.repository.count(Example.of(sample)));
    repository.delete(paymentVoucher);
  }

  @Test
  public void testConfirmAuditTotal() {
    PurchaseInvoice paymentVoucher = fakePaymentVoucher();
    paymentVoucher.confirmAuditTotal(BigDecimal.valueOf(1000));
    Assert.assertEquals(BigDecimal.valueOf(1000), paymentVoucher.getAuditTotal());
    Assert.assertEquals(BigDecimal.valueOf(1000), paymentVoucher.getNotAuditTotal());
    Assert.assertEquals(Invoice.付款确认, ((PurchaseInvoiceImpl) paymentVoucher).getStatus());
  }
}
