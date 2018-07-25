package com.jzqh.rzzl2.basicinfomanagement.customer;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.Address;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.BankAccount;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.Contact;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.ShareholderInfo;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @Description:合作伙伴基础信息模型接口，维护公司信息
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
public interface Company {
    /**
     * 添加公司地址信息
     *
     * @param address
     */
    void addAddress(Address address);

    /**
     * 移除公司地址信息
     *
     * @param address
     */
    void deleteAddress(Address address);

    /**
     * 添加联系人
     *
     * @param contact
     */
    void addContacts(Contact contact);

    /**
     * 删除联系人
     *
     * @param contact
     */
    void deleteContact(Contact contact);

    /**
     * 添加银行账户
     *
     * @param bankAccount
     */
    void addBankAccount(BankAccount bankAccount);

    /**
     * 删除银行账户信息
     *
     * @param bankAccount
     */
    void deleteBankAccount(BankAccount bankAccount);

    /**
     * 添加股东信息
     *
     * @param shareholderInfo
     */
    void addShareHolder(ShareholderInfo shareholderInfo);

    /**
     * 移除股东信息
     *
     * @param shareholderInfo
     */
    void deleteShareHolder(ShareholderInfo shareholderInfo);

    /**
     * 添加公司的基础信息
     */
    void addInfo();

    /**
     * 更新公司基础信息
     */
    void updateInfo();

    /***
     * 删除公司信息
     *
     */
    void deleteInfo();

    String getPartnerName();

    String getPartnerNature();

    String getPartnerType();

    String getLegalRepresentative();

    BigDecimal getRegisteredCapital();

    BigDecimal getContributedCapital();

    String getCurrency();

    String getRunningState();

    Date getRegisteredDate();

    String getRegisteredNo();

    String getOrganizationNo();

    String getTaxpayerNo();

    String getUniSocialNo();

    String getCompanyType();

    String getIndustry();

    Date getValidateDate();

    String getRegisteredAuthority();

    String getDistrict();

    String getEngName();

    String getUsedName();

    String getRunningWay();

    String getStaffSize();

    String getPeriod();

    String getRegisteredAddress();

    String getScope();

    String getSource();

    Set<Address> getAddressSet();

    Set<BankAccount> getBankAccounts();

    Set<ShareholderInfo> getShareholderInfoSet();

    Set<Contact> getContactSet();
}
