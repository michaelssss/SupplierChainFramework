package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.business.basicinfomanagement.domain.*;
import com.michaelssss.business.basicinfomanagement.respository.CompanyRepository;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


/**
 * Description:
 *
 * @Author:
 * @Date 2018/7/9
 */
public class CompanyTest extends SpringBootTestBasic {

    @Autowired
    private CompanyRepository repository;

    public BankAccount getBankAccount() {
        BankAccount bankAccount = new BankAccount();
        bankAccount.setAccountName("中国测试账户名称");
        bankAccount.setBankAccount("423252566655442");
        bankAccount.setBankName("中国银行");
        bankAccount.setBankFullName("中国银行深圳分行");
        bankAccount.setCurrency("RMB");
        bankAccount.setIsDefault("yes");
        bankAccount.setRemark("没有备注");
        return bankAccount;
    }

    public Contact getContact() {
        Contact contact = new Contact();
        contact.setContactType("一般联系人");
        contact.setDepartment("财务部");
        contact.setEmail("125525@sina.com");
        contact.setMobilePhone("1366245225");
        contact.setName("张三");
        contact.setPosition("经理");
        return contact;
    }

    public ShareholderInfo getShareholder() {
        ShareholderInfo shareholderInfo = new ShareholderInfo();
        shareholderInfo.setShareholderName("TONY");
        shareholderInfo.setFundedOfRatio("10");
        shareholderInfo.setCurrency("RMB");
        return shareholderInfo;
    }

    public Address getAddress() {
        Address address = Address.builder().addressType(Long.toString(1)).area(Long.toString(1)).city(Long.toString(2l))
                .detail("待定").province(Long.toString(1L)).remark("测试").build();
        return address;
    }

    public Company getCompany() {
        Set addressSet = new HashSet<Address>();
        addressSet.add(getAddress());
        Set bankAccount = new HashSet<BankAccount>();
        bankAccount.add(getBankAccount());
        Set contactSet = new HashSet<Contact>();
        contactSet.add(getContact());
        Set shareholder = new HashSet<ShareholderInfo>();
        shareholder.add(getShareholder());
        Company company = CompanyImpl.builder().companyName("测试公司")
                .nature("上市公司").legalRepresentative("张三").registeredCapital(BigDecimal.valueOf(1001))
                .contributedCapital(BigDecimal.valueOf(1001)).currency("人民币").runningState("在营")
                .registeredDate(new Date()).registeredNo("1000").organizationNo("100010")
                .taxpayerNo("100").uniSocialNo("153321").companyType("1").industry("测试").validateDate(new Date())
                .registeredAuthority("水电费").district("深圳").engName("dine")
                .usedName("超神").runningWay("不知道").staffSize("上市")
                .period("222").registeredAddress("深圳南山").scope("农业")
                .source("介绍所").addressSet(addressSet).shareholderInfoSet(shareholder).contactSet(contactSet).bankAccounts(bankAccount).build();
        return company;

    }

    @Test
    public void addCompany() {
        Company company = getCompany();
        company.save();

        repository.delete(repository.findOne(Example.of(CompanyImpl.builder().companyName(company.getCompanyName()).build())));
    }

    @Test
    public void addAddress() {
        Address address = Address.builder().addressType(Long.toString(2l)).area(Long.toString(3l)).city(Long.toString(2l))
                .detail("待定").province(Long.toString(1L)).remark("测试").build();


        Address address1 = Address.builder().addressType(Long.toString(1)).area(Long.toString(1)).city(Long.toString(2l))
                .detail("待定").province(Long.toString(1L)).remark("测试").build();
        Set<Address> addressSet = new HashSet<>();
        addressSet.add(address1);
        Company company = CompanyImpl.builder().id(2l).companyName("测试公司更新")
                .nature("上市公司").legalRepresentative("张三").registeredCapital(BigDecimal.valueOf(1001))
                .contributedCapital(BigDecimal.valueOf(1001)).currency("人民币").runningState("在营")
                .registeredDate(new Date()).registeredNo("1000").organizationNo("100010")
                .taxpayerNo("100").uniSocialNo("153321").companyType("1").industry("测试").validateDate(new Date())
                .registeredAuthority("水电费").district("深圳").engName("dine")
                .usedName("超神").runningWay("不知道").staffSize("上市")
                .period("222").registeredAddress("深圳南山").scope("农业")
                .source("介绍所").addressSet(addressSet).build();
        company.save();
    }


}
