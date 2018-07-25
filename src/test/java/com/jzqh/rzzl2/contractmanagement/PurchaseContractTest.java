package com.jzqh.rzzl2.contractmanagement;

import com.jzqh.rzzl2.SpringBootTestBasic;
import com.jzqh.rzzl2.contractmanagement.impl.PurchaseContractImpl;
import com.jzqh.rzzl2.contractmanagement.repository.PurchaseContactRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/18
 */
public class PurchaseContractTest extends SpringBootTestBasic {

    @Autowired
    private PurchaseContactRepository purchaseOrderRepository;


    public PurchaseContract getPurchase() {
        PurchaseContract purchaseContract = PurchaseContractImpl.builder().mainContractId(1l).mainContractName("测试合同框架")
                .projectName("测试项目").versionNo("100").contractNo("10001").contractName("采购合同测试").purchaseOrderId(1l).contractNo(PurchaseContractImpl.builder().build().getPurchaseContractNo())
                .purchaseOrderName("采购订单").purchaseOrderNo("CG-DD-100000").proxyCompany("代理公司a").warehouseId(1l).warehouseName("深圳仓库")
                .deliveryTime(new Date()).qa("阿long").prepaymentDatetime(new Date()).amtPrepayment(BigDecimal.valueOf(1000.0))
                .amtPayBeforeDelivery(BigDecimal.valueOf(1200)).balancePayDatetime(new Date()).balancePayWay("保证金抵扣")
                .afterSalesServices("不用服务").invoicingDatetime(new Date()).overdueCondition("支付时间未支付").contractSigningDate(new Date())
                .contractStartDate(new Date()).contractEndDate(new Date()).auditState("1").build();
        return purchaseContract;
    }


    @Test
    public void addPurchaseContract() {
        PurchaseContract purchaseContract = getPurchase();
        purchaseContract.addPurchaseContract();
        purchaseContract.deletePurchaseContract();

    }

    @Test
    public void updatePurchaseContract() {
        PurchaseContract purchaseContract = getPurchase();
        purchaseContract.addPurchaseContract();
        ((PurchaseContractImpl) purchaseContract).setContractName("修改采购合同");
        purchaseContract.updatePurchaseContract();
        PurchaseContractImpl purchase = purchaseOrderRepository.findOne(((PurchaseContractImpl) purchaseContract).getId());
        Assert.assertEquals("修改采购合同", purchase.getContractName());
        purchaseContract.deletePurchaseContract();
    }

    @Test
    public void deletePurchaseContract() {
        PurchaseContract purchaseContract = getPurchase();
        purchaseContract.addPurchaseContract();
        purchaseContract.deletePurchaseContract();
        PurchaseContractImpl purchase = purchaseOrderRepository.findOne(((PurchaseContractImpl) purchaseContract).getId());
        Assert.assertNull(purchase);
    }

    @Test
    public void confirmFrameContract() {
        PurchaseContract purchaseContract = getPurchase();
        purchaseContract.addPurchaseContract();
        purchaseContract.confirmPurchaseContract();
        PurchaseContractImpl purchase = purchaseOrderRepository.findOne(((PurchaseContractImpl) purchaseContract).getId());
        Assert.assertEquals(PurchaseContract.Confirm, purchase.getAuditState());
        purchaseContract.deletePurchaseContract();
    }
}
