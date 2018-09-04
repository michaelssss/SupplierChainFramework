package com.michaelssss.rzzl2.basicinfomanagement.customer;

import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.Address;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.BankAccount;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.Contact;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.ShareholderInfo;

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

    /**
     * 公司名称
     */
    String getPartnerName();

    /**
     * 合作伙伴性质
     */
    String getPartnerNature();

    /**
     * 合作伙伴类型
     */
    String getPartnerType();

    /**
     * 法定代表人
     */
    String getLegalRepresentative();

    /**
     * 注册资本
     */
    BigDecimal getRegisteredCapital();

    /**
     * 注册资本
     */
    BigDecimal getContributedCapital();

    /**
     * 币种
     */
    String getCurrency();

    /**
     * 经营状态
     */
    String getRunningState();

    /**
     * 成立日期
     */
    Date getRegisteredDate();

    /**
     * 注册号
     */
    String getRegisteredNo();

    /**
     * 组织机构代码
     */
    String getOrganizationNo();

    /**
     * 纳税人识别号
     */
    String getTaxpayerNo();

    /**
     * 统一社会信用代码
     */
    String getUniSocialNo();

    /**
     * 公司类型
     */
    String getCompanyType();

    /**
     * 所属行业
     */
    String getIndustry();

    /**
     * 核准日期
     */
    Date getValidateDate();

    /**
     * 登记机关
     */
    String getRegisteredAuthority();

    /**
     * 所属地区
     */
    String getDistrict();

    /**
     * 英文名
     */
    String getEngName();

    /**
     * 曾用名
     */
    String getUsedName();

    /**
     * 经营方式
     */
    String getRunningWay();

    /**
     * 员工规模
     */
    String getStaffSize();

    /**
     * 营业期限
     */
    String getPeriod();

    /**
     * 注册地址
     */
    String getRegisteredAddress();

    /**
     * 经营范围
     */
    String getScope();

    /**
     * 合作伙伴来源
     */
    String getSource();

    /**
     * 公司地址列表
     *
     * @return
     */
    Set<Address> getAddressSet();

    /**
     * 银行账户列表
     *
     * @return
     */
    Set<BankAccount> getBankAccounts();

    /**
     * 股东信息
     *
     * @return
     */
    Set<ShareholderInfo> getShareholderInfoSet();

    /**
     * 联系人列表
     *
     * @return
     */
    Set<Contact> getContactSet();
}
