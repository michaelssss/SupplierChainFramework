package com.jzqh.rzzl2.accountingmanagement;

import com.jzqh.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;

public class ReceiptVoucherImplTest extends SpringBootTestBasic {
    @Autowired
    private ReceiptVoucherRepository repository;

    static ReceiptVoucherImpl fakeReceiptVoucher() {
        ReceiptVoucherBuilder builder = ReceiptVoucherBuilder.aReceiptVoucherImpl();
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
        ReceiptVoucherImpl receiptVoucher = repository.saveAndFlush(fakeReceiptVoucher());
        ReceiptVoucherImpl sample = new ReceiptVoucherImpl();
        sample.setSalesOrderCode("testCode");
        Assert.assertEquals(1L, this.repository.count(Example.of(sample)));
        repository.delete(receiptVoucher);
    }

    @Test
    public void testConfirmAuditTotal() {
        ReceiptVoucher receiptVoucher = fakeReceiptVoucher();
        receiptVoucher.confirmAuditTotal(BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(1000), receiptVoucher.getAuditTotal());
        Assert.assertEquals(BigDecimal.valueOf(1000), receiptVoucher.getNotAuditTotal());
        Assert.assertEquals(Voucher.RECEIVEDCONFIRM, ((ReceiptVoucherImpl) receiptVoucher).getStatus());
    }
}
