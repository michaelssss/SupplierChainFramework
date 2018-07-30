package com.jzqh.rzzl2.basicinfomanagement.customer;

import com.jzqh.SpringBootTestBasic;
import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplyPurchaseImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.PurchaserImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
public class Purchase extends SpringBootTestBasic {

    @Test
    public void addPurchase() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Purchaser purchaser = PurchaserImpl.builder().build();
        purchaser.addInfo();
        ((PurchaserImpl) purchaser).setCompany(company);
        purchaser.addInfo();
        purchaser.deleteInfo();
    }

    @Test
    public void updatePurchase() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(2l);
        Purchaser purchaser = PurchaserImpl.builder().id(1l).company(company).build();
        purchaser.updateInfo();
    }


    @Test
    public void permit() {

        EntryApplyPurchaseImpl entryApplyPurchase = EntryApplyPurchaseImpl.builder().accessStation("1").accessTime(new Date())
                .actualController("张三").businessLicense("223").changeRecord("无").contactName("lucy").contactPhone("13264855991")
                .currency("RMB").idCard("1433557846632566").legalRepresentative("tony").officePicture("东方闪电").partnerName("测试公司")
                .officePicture("").partnerNature("").position("经理").registerdCapital(BigDecimal.valueOf(10012)).registeredCapital(BigDecimal.valueOf(10012))
                .registeredAddress("深圳").build();
        entryApplyPurchase.addInfo();
    }


}
