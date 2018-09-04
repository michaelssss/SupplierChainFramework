package com.michaelssss.rzzl2.contractmanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.rzzl2.contractmanagement.impl.SalesContractImpl;
import com.michaelssss.rzzl2.contractmanagement.repository.SalesContractRepository;
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
public class SalesContractTest extends SpringBootTestBasic {

    @Autowired
    private SalesContractRepository repository;

    public SalesContract getSalesContract() {
        SalesContract salesContract = SalesContractImpl.builder().projectNo("XM-10000").projectName("测试项目").frameContractId(1l)
                .frameContractName("框架合同").frameContractNo("KT1000").salesOrderId(1l).salesContractNo("WW100").salesOrderName("销售订单")
                .salesContractName("销售合同").transportWay("灰机").deliveryCostBearer("自费").deliveryEndDate(new Date()).amtTotal(BigDecimal.valueOf(100))
                .currency("RMB").effectiveTime(new Date()).signTime(new Date()).invalidTime(new Date()).auditState("1").build();
        return salesContract;
    }

    @Test
    public void addSalesContract() {
        SalesContract salesContract = getSalesContract();
        salesContract.addSalesContract();
        salesContract.deleteSalesContract();
    }

    @Test
    public void updateSalesContract() {
        SalesContract salesContract = getSalesContract();
        salesContract.addSalesContract();
        ((SalesContractImpl) salesContract).setSalesContractName("修改销售订单合同");
        salesContract.updateSalesContract();
        SalesContractImpl sales = repository.findOne(((SalesContractImpl) salesContract).getId());
        Assert.assertEquals("修改销售订单合同", sales.getSalesContractName());
        salesContract.deleteSalesContract();
    }

    @Test
    public void deleteSalesContract() {
        SalesContract salesContract = getSalesContract();
        salesContract.addSalesContract();
        salesContract.deleteSalesContract();
        SalesContractImpl sales = repository.findOne(((SalesContractImpl) salesContract).getId());
        Assert.assertNull(sales);
    }

    @Test
    public void confirmSalesContract() {
        SalesContract salesContract = getSalesContract();
        salesContract.addSalesContract();
        salesContract.confirmSalesContract();
        SalesContractImpl sales = repository.findOne(((SalesContractImpl) salesContract).getId());
        Assert.assertEquals(SalesContract.Confirm, sales.getAuditState());
        salesContract.deleteSalesContract();
    }
}
