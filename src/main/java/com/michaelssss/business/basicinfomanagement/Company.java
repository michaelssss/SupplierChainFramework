package com.michaelssss.business.basicinfomanagement;

import com.michaelssss.business.basicinfomanagement.domain.Address;
import com.michaelssss.business.basicinfomanagement.domain.BankAccount;
import com.michaelssss.business.basicinfomanagement.domain.Contact;
import com.michaelssss.business.basicinfomanagement.domain.ShareholderInfo;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Set;

/**
 * @Description:合作伙伴基础信息模型接口，维护公司信息 @Author:tanshaoxing @Date:2018/7/11
 */
public interface Company {

  Long getId();

  /** 添加公司地址信息 */
  void addAddress(Address address);

  /** 移除公司地址信息 */
  void deleteAddress(Address address);

  /** 添加联系人 */
  void addContacts(Contact contact);

  /** 删除联系人 */
  void deleteContact(Contact contact);

  /** 添加银行账户 */
  void addBankAccount(BankAccount bankAccount);

  /** 删除银行账户信息 */
  void deleteBankAccount(BankAccount bankAccount);

  /** 添加股东信息 */
  void addShareHolder(ShareholderInfo shareholderInfo);

  /** 移除股东信息 */
  void deleteShareHolder(ShareholderInfo shareholderInfo);

  /** 添加公司的基础信息 */
  void save();

  /** 公司名称 */
  String getCompanyName();

  /** 合作伙伴性质 */
  String getNature();

  /** 合作伙伴类型 */
  String getPartnerType();

  /** 法定代表人 */
  String getLegalRepresentative();

  /** 注册资本 */
  BigDecimal getRegisteredCapital();

  /** 注册资本 */
  BigDecimal getContributedCapital();

  /** 币种 */
  String getCurrency();

  /** 经营状态 */
  String getRunningState();

  /** 成立日期 */
  Date getRegisteredDate();

  /** 注册号 */
  String getRegisteredNo();

  /** 组织机构代码 */
  String getOrganizationNo();

  /** 纳税人识别号 */
  String getTaxpayerNo();

  /** 统一社会信用代码 */
  String getUniSocialNo();

  /** 公司类型 */
  String getCompanyType();

  /** 所属行业 */
  String getIndustry();

  /** 核准日期 */
  Date getValidateDate();

  /** 登记机关 */
  String getRegisteredAuthority();

  /** 所属地区 */
  String getDistrict();

  /** 英文名 */
  String getEngName();

  /** 曾用名 */
  String getUsedName();

  /** 经营方式 */
  String getRunningWay();

  /** 员工规模 */
  String getStaffSize();

  /** 营业期限 */
  String getPeriod();

  /** 注册地址 */
  String getRegisteredAddress();

  /** 经营范围 */
  String getScope();

  /** 合作伙伴来源 */
  String getSource();

  String getHistoryId();

  /** 公司地址列表 */
  Set<Address> getAddressSet();

  /** 银行账户列表 */
  Set<BankAccount> getBankAccounts();

  /** 股东信息 */
  Set<ShareholderInfo> getShareholderInfoSet();

  /** 联系人列表 */
  Set<Contact> getContactSet();

  /** 产生一条审核记录，生成一条新记录信息 */
  void applyAudit();

  /**
   * 审计完成，将其状态变更
   */
  void validated();
}
