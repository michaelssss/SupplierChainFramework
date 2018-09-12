package com.michaelssss.business.accountingmanagement;

import com.michaelssss.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;

public class SalesInvoiceImplTest extends SpringBootTestBasic {
    @Autowired
    private SalesInvoiceCatalog repository;

    static SalesInvoiceImpl fakeReceiptVoucher() {
        SalesInvoiceBuilder builder = SalesInvoiceBuilder.aReceiptVoucherImpl();
        builder
                .type("")
                .amount(BigDecimal.valueOf(2000))
                .salesOrderCode("testCode")
                .paymentMethod("BankTransfer")
                .receipts(new TreeSet<>())
                .transferFinishDate(new Date());
        return builder.build();
    }

    @Test
    public void addPaymentVoucher() {
        SalesInvoiceImpl receiptVoucher = repository.saveAndFlush(fakeReceiptVoucher());
        SalesInvoiceImpl sample = new SalesInvoiceImpl();
        sample.setSalesOrderCode("testCode");
        Assert.assertEquals(1L, this.repository.count(Example.of(sample)));
        repository.delete(receiptVoucher);
    }

    @Test
    public void testConfirmAuditTotal() {
        SalesInvoice receiptVoucher = fakeReceiptVoucher();
        receiptVoucher.confirmAuditTotal(BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(1000), receiptVoucher.getAuditTotal());
        Assert.assertEquals(BigDecimal.valueOf(1000), receiptVoucher.getNotAuditTotal());
        Assert.assertEquals(Invoice.收款确认, ((SalesInvoiceImpl) receiptVoucher).getStatus());
    }
}
