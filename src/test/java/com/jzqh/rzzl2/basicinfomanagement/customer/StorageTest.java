package com.jzqh.rzzl2.basicinfomanagement.customer;

import com.jzqh.SpringBootTestBasic;
import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.StorageImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.junit.Test;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/10
 */
public class StorageTest extends SpringBootTestBasic {
    @Test
    public void addStorage() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Storage storage = StorageImpl.builder().build();
        storage.addInfo();
        ((StorageImpl) storage).setCompany(company);
        storage.updateInfo();
    }

    @Test
    public void updateFunding() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(2l);
        Storage storage = StorageImpl.builder().id(1l).company(company).build();
        storage.updateInfo();
    }

    @Test
    public void deleteFunding() {

        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Storage storage = StorageImpl.builder().build();
        storage.addInfo();
        ((StorageImpl) storage).setCompany(company);
        storage.deleteInfo();
    }
}
