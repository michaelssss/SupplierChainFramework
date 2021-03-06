package com.michaelssss.business.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.respository.CompanyRepository;
import com.michaelssss.business.basicinfomanagement.service.CompanyHistoryService;
import io.swagger.annotations.ApiModelProperty;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.runtime.ProcessInstance;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;

/**
 * Description: 客户基础信息新增、修改、删除 @Author:tsx @Date 2018.7.4
 */
@Entity
@ToString
@Builder
@Data
@Table(
    name = "company",
    indexes = {
        @Index(
            name = "idx_companyNameHistoryId",
            columnList = "company_name,history_id",
            unique = true)
    })
public class CompanyImpl implements Company, Serializable {

  private static final long serialVersionUID = -7485430596329993850L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @ApiModelProperty(hidden = true)
  private Long id;

  @Column(length = 64, name = "company_name")
  @ApiModelProperty(value = "公司名称", example = "测试公司")
  private String companyName;

  @ApiModelProperty(value = "国别", example = "中国")
  private String nature;

  @ApiModelProperty(value = "合作伙伴类型", example = "商业伙伴")
  private String partnerType;

  @ApiModelProperty(value = "法定代表人", example = "Michaelssss")
  private String legalRepresentative;

  @ApiModelProperty(value = "注册资本", example = "102333213321.22")
  private BigDecimal registeredCapital;

  @ApiModelProperty(value = "注册资本", example = "102333213321.22")
  private BigDecimal contributedCapital;

  @ApiModelProperty(value = "币种", example = "人民币")
  private String currency;

  @ApiModelProperty(value = "经营状态", example = "正常")
  private String runningState;

  @ApiModelProperty(value = "成立日期", example = "2018-09-14T03:21:03.000Z")
  private Date registeredDate;

  @ApiModelProperty(value = "注册号", example = "1234567890")
  private String registeredNo;

  @ApiModelProperty(value = "组织机构代码", example = "1234567890")
  private String organizationNo;

  @ApiModelProperty(value = "纳税人识别号", example = "1234567890")
  private String taxpayerNo;

  @ApiModelProperty(value = "统一社会信用代码", example = "1234567890")
  private String uniSocialNo;

  @ApiModelProperty(value = "公司类型", example = "假公司")
  private String companyType;

  @ApiModelProperty(value = "所属行业", example = "金融证券")
  private String industry;

  @ApiModelProperty(value = "核准日期", example = "2018-09-14T03:21:03.000Z")
  private Date validateDate;

  @ApiModelProperty(value = "登记机关", example = "税务局")
  private String registeredAuthority;

  @ApiModelProperty(value = "所属地区", example = "深圳")
  private String district;

  @ApiModelProperty(value = "英文名", example = "TestCompany")
  private String engName;

  @ApiModelProperty(value = "曾用名", example = "NO")
  private String usedName;

  @ApiModelProperty(value = "经营方式", example = "Test")
  private String runningWay;

  @ApiModelProperty(value = "员工规模", example = "200万")
  private String staffSize;

  @ApiModelProperty(value = "营业期限", example = "十年")
  private String period;

  @ApiModelProperty(value = "注册地址", example = "深圳市")
  private String registeredAddress;

  @ApiModelProperty(value = "经营范围", example = "烟草")
  private String scope;

  @ApiModelProperty(value = "合作伙伴来源", example = "业务员")
  private String source;

  @Column(length = 64, name = "history_id")
  @ApiModelProperty(value = "历史ID", hidden = true)
  private String historyId;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ApiModelProperty(value = "公司地址")
  private Set<Address> addressSet;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ApiModelProperty(value = "银行账户列表")
  private Set<BankAccount> bankAccounts;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ApiModelProperty(value = "股东信息列表")
  private Set<ShareholderInfo> shareholderInfoSet;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @ApiModelProperty(value = "联系人列表")
  private Set<Contact> contactSet;

  @Override
  public void addAddress(Address address) {
    this.addressSet.add(address);
  }

  @Override
  public void deleteAddress(Address address) {
    addressSet.remove(address);
  }

  @Override
  public void addContacts(Contact contact) {
    contactSet.add(contact);
  }

  @Override
  public void deleteContact(Contact contact) {
    contactSet.remove(contact);
  }

  @Override
  public void addBankAccount(BankAccount bankAccount) {
    this.bankAccounts.add(bankAccount);
  }

  @Override
  public void deleteBankAccount(BankAccount bankAccount) {
    bankAccounts.remove(bankAccount);
  }

  @Override
  public void addShareHolder(ShareholderInfo shareholderInfo) {
    shareholderInfoSet.add(shareholderInfo);
  }

  @Override
  public void deleteShareHolder(ShareholderInfo shareholderInfo) {
    shareholderInfoSet.remove(shareholderInfo);
  }

  @Override
  public void save() {
    CompanyImpl company = CompanyImpl.builder().build();
    company.setCompanyName(this.companyName);
    // 创建实例
    Example<CompanyImpl> ex = Example.of(company);
    if (SpringContextHolder.getBean(CompanyRepository.class).exists(ex)) {
      throw new BusinessException("公司名称已存在");
    }
    this.setHistoryId(Long.toString(System.currentTimeMillis()));
    this.validateDate = null;
    SpringContextHolder.getBean(CompanyRepository.class).saveAndFlush(this);
  }

  @Override
  public String getCompanyName() {
    return companyName;
  }

  @Override
  public String getNature() {
    return nature;
  }

  @Override
  public String getPartnerType() {
    return partnerType;
  }

  @Override
  public String getLegalRepresentative() {
    return legalRepresentative;
  }

  @Override
  public BigDecimal getRegisteredCapital() {
    return registeredCapital;
  }

  @Override
  public BigDecimal getContributedCapital() {
    return contributedCapital;
  }

  @Override
  public String getCurrency() {
    return currency;
  }

  @Override
  public String getRunningState() {
    return runningState;
  }

  @Override
  public Date getRegisteredDate() {
    return registeredDate;
  }

  @Override
  public String getRegisteredNo() {
    return registeredNo;
  }

  @Override
  public String getOrganizationNo() {
    return organizationNo;
  }

  @Override
  public String getTaxpayerNo() {
    return taxpayerNo;
  }

  @Override
  public String getUniSocialNo() {
    return uniSocialNo;
  }

  @Override
  public String getCompanyType() {
    return companyType;
  }

  @Override
  public String getIndustry() {
    return industry;
  }

  @Override
  public Date getValidateDate() {
    return validateDate;
  }

  @Override
  public String getRegisteredAuthority() {
    return registeredAuthority;
  }

  @Override
  public String getDistrict() {
    return district;
  }

  @Override
  public String getEngName() {
    return engName;
  }

  @Override
  public String getUsedName() {
    return usedName;
  }

  @Override
  public String getRunningWay() {
    return runningWay;
  }

  @Override
  public String getStaffSize() {
    return staffSize;
  }

  @Override
  public String getPeriod() {
    return period;
  }

  @Override
  public String getRegisteredAddress() {
    return registeredAddress;
  }

  @Override
  public String getScope() {
    return scope;
  }

  @Override
  public String getSource() {
    return source;
  }

  @Override
  public Set<Address> getAddressSet() {
    return Collections.unmodifiableSet(addressSet);
  }

  @Override
  public Set<BankAccount> getBankAccounts() {
    return Collections.unmodifiableSet(bankAccounts);
  }

  @Override
  public Set<ShareholderInfo> getShareholderInfoSet() {
    return Collections.unmodifiableSet(shareholderInfoSet);
  }

  @Override
  public Set<Contact> getContactSet() {
    return Collections.unmodifiableSet(contactSet);
  }

  @Override
  public void applyAudit() {
    Company company = SpringContextHolder.getBean(CompanyHistoryService.class).addNewRecord(this);
    RuntimeService runtimeService = SpringContextHolder.getBean(RuntimeService.class);
    // 根据ProcessKey得到新的ProcessInstance，然后将当前需要审批的公司ID传入，审批页面只是单纯的做查询
    ProcessInstance companyValidateProcess =
        runtimeService.startProcessInstanceByKey("CompanyValidateProcess");
    Map<String, Object> map = new HashMap<>();
    map.put("companyName", company.getCompanyName());
    map.put("historyId", company.getHistoryId());
    runtimeService.setVariablesLocal(companyValidateProcess.getId(), map);
  }

  @Override
  public void validated() {
    this.validateDate = new Date();
  }

  @Override
  protected Object clone() throws CloneNotSupportedException {
    CompanyImpl company2 = CompanyImpl.builder().build();
    BeanUtils.copyProperties(this, company2, "id");
    Set<Address> addresses = new HashSet<>();
    Set<ShareholderInfo> shareholderInfos = new HashSet<>();
    Set<BankAccount> bankAccounts = new HashSet<>();
    Set<Contact> contacts = new HashSet<>();
    for (Address address : this.getAddressSet()) {
      Address address1 = new Address();
      BeanUtils.copyProperties(address, address1, "id");
      addresses.add(address1);
    }
    for (ShareholderInfo shareholderInfo : this.getShareholderInfoSet()) {
      ShareholderInfo shareholderInfo1 = new ShareholderInfo();
      BeanUtils.copyProperties(shareholderInfo, shareholderInfo1, "id");
      shareholderInfos.add(shareholderInfo1);
    }
    for (BankAccount bankAccount : this.getBankAccounts()) {
      BankAccount bankAccount1 = new BankAccount();
      BeanUtils.copyProperties(bankAccount, bankAccount1, "id");
      bankAccounts.add(bankAccount1);
    }
    for (Contact contact : this.getContactSet()) {
      Contact contact1 = new Contact();
      BeanUtils.copyProperties(contact, contact1, "id");
      contacts.add(contact1);
    }
    company2.setAddressSet(addresses);
    company2.setShareholderInfoSet(shareholderInfos);
    company2.setContactSet(contacts);
    company2.setBankAccounts(bankAccounts);
    return company2;
  }
}
