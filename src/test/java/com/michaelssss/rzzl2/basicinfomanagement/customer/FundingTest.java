package com.michaelssss.rzzl2.basicinfomanagement.customer;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.FundingPartyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.junit.Test;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
public class FundingTest extends SpringBootTestBasic {
    @Test
    public void addFunding() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Funding funding = FundingPartyImpl.builder().build();
        funding.addInfo();
        ((FundingPartyImpl) funding).setCompany(company);
        funding.addInfo();
        funding.deleteInfo();
    }

    @Test
    public void updateFunding() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Funding funding = FundingPartyImpl.builder().Id(1l).company(company).build();
        funding.updateInfo();
    }

    @Test
    public void deleteFunding() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Funding funding = FundingPartyImpl.builder().build();
        funding.addInfo();
        ((FundingPartyImpl) funding).setCompany(company);
        funding.deleteInfo();
    }

}
