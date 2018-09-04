package com.michaelssss.rzzl2.accountingmanagement;

import com.michaelssss.SpringBootTestBasic;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.TreeSet;

public class PaymentVoucherImplTest extends SpringBootTestBasic {
    @Autowired
    private PaymentVoucherRepository repository;

    static PaymentVoucherImpl fakePaymentVoucher() {
        PaymentVoucherBuilder builder = PaymentVoucherBuilder.aPaymentVoucherImpl();
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
        PaymentVoucherImpl paymentVoucher = repository.saveAndFlush(fakePaymentVoucher());
        PaymentVoucherImpl sample = new PaymentVoucherImpl();
        sample.setPurchaseCode("testCode");
        Assert.assertEquals(1L, this.repository.count(Example.of(sample)));
        repository.delete(paymentVoucher);
    }

    @Test
    public void testConfirmAuditTotal() {
        PaymentVoucher paymentVoucher = fakePaymentVoucher();
        paymentVoucher.confirmAuditTotal(BigDecimal.valueOf(1000));
        Assert.assertEquals(BigDecimal.valueOf(1000), paymentVoucher.getAuditTotal());
        Assert.assertEquals(BigDecimal.valueOf(1000), paymentVoucher.getNotAuditTotal());
        Assert.assertEquals(Voucher.PAYCONFIRM, ((PaymentVoucherImpl) paymentVoucher).getStatus());
    }
}
