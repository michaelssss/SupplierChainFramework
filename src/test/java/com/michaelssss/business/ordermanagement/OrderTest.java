package com.michaelssss.business.ordermanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.SpringContextHolder;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.data.domain.Example;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;

public class OrderTest extends SpringBootTestBasic {
    static EntrustedOrderImpl fakeEntrustedOrder() {
        EntrustedOrderBuilder builder = EntrustedOrderBuilder.anEntrustedOrderImpl();
        builder
                .mainContractId("testMainContractId")
                .mainContractName("testMainContractName")
                .orderDate(new Date())
                .deliveryMethod("post")
                .currency("RMB")
                .receivedInfo(FakeReceivedInfo.getMock())
                .entrustedCompany("testCompany")
                .supplierName("testSupplier")
                .supplierContactName("Michaelssss")
                .supplierContactPhone("18124601061")
                .deliveryCostBearer("testCompany");
        return builder.build();
    }

    static SalesOrderImpl fakeSalesOrder() {
        SalesOrderBuilder builder = SalesOrderBuilder.aSalesOrderImpl();
        builder
                .buyer("Michaelssss")
                .entrustedCode(fakeEntrustedOrder().getCode())
                .guaranteeMoneyOffset(BigDecimal.valueOf(20.00))
                .currency("RMB")
                .orderDate(new Date())
                .payChannel("Alipay")
                .paymentType("pay_immediate")
                .prePayTotal(BigDecimal.valueOf(20000))
                .source("onlineShop")
                .serviceCharge(BigDecimal.valueOf(20))
                .goods(FakeOrderGood.fakeOrderGoodList());
        return builder.build();
    }

    static PurchaseOrderImpl fakePurchaseOrder() {
        PurchaseOrderBuilder builder = PurchaseOrderBuilder.aPurchaseOrderImpl();
        return builder.receivedInfo(FakeReceivedInfo.getMock())
                .currency("RMB")
                .orderDate(new Date())
                .deliveryDate(new Date())
                .entrustedCode(fakeEntrustedOrder().getCode())
                .goods(FakeOrderGood.fakeOrderGoodList())
                .build();
    }

    /**
     * 添加委托单测试
     */
    @Test
    @Transactional
    public void testAddEntrustedOrder() {
        EntrustedOrderImpl fake = null;
        EntrustedOrderRepository repository = SpringContextHolder.getBean(EntrustedOrderRepository.class);
        fake = repository.saveAndFlush(fakeEntrustedOrder());
        EntrustedOrderImpl sample = new EntrustedOrderImpl();
        sample.setMainContractId("testMainContractId");
        Assert.assertTrue(repository.count(Example.of(sample)) > 0L);
        repository.delete(fake);
    }

    @Test
    @Transactional
    public void testAddSalesOrder() {
        EntrustedOrderRepository repository1 = SpringContextHolder.getBean(EntrustedOrderRepository.class);
        EntrustedOrderImpl fake1 = repository1.saveAndFlush(fakeEntrustedOrder());
        SalesOrderImpl fake = null;
        SalesOrderRepository repository = SpringContextHolder.getBean(SalesOrderRepository.class);
        fake = repository.saveAndFlush(fakeSalesOrder());
        SalesOrderImpl sample = new SalesOrderImpl();
        sample.setEntrustedCode(fakeEntrustedOrder().getCode());
        Assert.assertTrue(repository.count(Example.of(sample)) > 0L);
        repository1.delete(fake1);
        repository.delete(fake);
    }

    @Test
    @Transactional
    public void testAddPurchaseOrder() {
        EntrustedOrderRepository repository1 = SpringContextHolder.getBean(EntrustedOrderRepository.class);
        EntrustedOrderImpl fake1 = repository1.saveAndFlush(fakeEntrustedOrder());
        PurchaseOrderImpl fake = null;
        PurchaseOrderRepository repository = SpringContextHolder.getBean(PurchaseOrderRepository.class);
        fake = repository.saveAndFlush(fakePurchaseOrder());
        PurchaseOrderImpl sample = new PurchaseOrderImpl();
        sample.setEntrustedCode(fakeEntrustedOrder().getCode());
        Assert.assertTrue(repository.count(Example.of(sample)) > 0L);
        repository1.delete(fake1);
        repository.delete(fake);
    }

    @Test
    public void testConfirm() {
        Order order = fakeEntrustedOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.confirm();
        Assert.assertEquals(Order.Confirm, order.getStatus());
        order = fakePurchaseOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.confirm();
        Assert.assertEquals(Order.Confirm, order.getStatus());
        order = fakeSalesOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.confirm();
        Assert.assertEquals(Order.Confirm, order.getStatus());
    }

    @Test
    public void testTerminate() {
        Order order = fakeEntrustedOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.terminate();
        Assert.assertEquals(Order.Terminate, order.getStatus());
        order = fakePurchaseOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.terminate();
        Assert.assertEquals(Order.Terminate, order.getStatus());
        order = fakeSalesOrder();
        Assert.assertEquals(Order.New, order.getStatus());
        order.terminate();
        Assert.assertEquals(Order.Terminate, order.getStatus());
    }
}
