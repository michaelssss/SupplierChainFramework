package com.michaelssss.rzzl2.basicinfomanagement.customer;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.SupplierImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.junit.Test;

/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/9
 */

public class SupplierTest extends SpringBootTestBasic {
    @Test
    public void addSupplier() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Supplier supplier = SupplierImpl.builder().build();
        supplier.addInfo();
        ((SupplierImpl) supplier).setCompany(company);
        supplier.updateInfo();
    }

    @Test
    public void updateSupplier() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Supplier supplier = SupplierImpl.builder().id(1l).company(company).build();
        supplier.updateInfo();
    }

    @Test
    public void deleteSupplier() {
        CompanyImpl company = SpringContextHolder.getBean(CompanyRepository.class).findOne(1l);
        Supplier supplier = SupplierImpl.builder().build();
        supplier.addInfo();
        ((SupplierImpl) supplier).setCompany(company);
        supplier.deleteInfo();
    }

}
