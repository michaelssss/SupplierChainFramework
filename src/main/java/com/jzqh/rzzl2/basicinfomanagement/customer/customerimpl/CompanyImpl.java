package com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl;

import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.customer.Company;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import com.jzqh.rzzl2.exception.ExistException;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * Description: 客户基础信息新增、修改、删除
 *
 * @Author:tsx
 * @Date 2018.7.4
 */
@Entity
@ToString
@Builder
@Data
@Table(name = "company")
public class CompanyImpl implements Company {

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Set<Contact> contacts;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String partnerName;//公司名称
    private String partnerNature;//合作伙伴性质
    private String partnerType;//合作伙伴类型
    private String legalRepresentative;//法定代表人
    private BigDecimal registeredCapital;//注册资本
    private BigDecimal contributedCapital;//注册资本
    private String currency;//币种
    private String runningState;//经营状态
    private Date registeredDate;//成立日期
    private String registeredNo;//注册号
    private String organizationNo;//组织机构代码
    private String taxpayerNo;//纳税人识别号
    private String uniSocialNo;//统一社会信用代码
    private String companyType;//公司类型
    private String industry;//所属行业
    private Date validateDate;//核准日期
    private String registeredAuthority;//登记机关
    private String district;//所属地区
    private String engName;//英文名
    private String usedName;//曾用名
    private String runningWay;//经营方式
    private String staffSize;//员工规模
    private String period;//营业期限
    private String registeredAddress;//注册地址
    private String scope;//经营范围
    private String source;//合作伙伴来源
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Set<Address> addressSet;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Set<BankAccount> bankAccounts;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
    private Set<ShareholderInfo> shareholderInfoSet;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "companyId")
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
    public void addInfo() {

        CompanyImpl company = CompanyImpl.builder().build();
        company.setPartnerName(this.partnerName);
        //创建实例
        Example<CompanyImpl> ex = Example.of(company);
        List<CompanyImpl> list = SpringContextHolder.getBean(CompanyRepository.class).findAll(ex);
        if (list.size() > 0) {
            throw new ExistException("公司名称已存在");
        }
        SpringContextHolder.getBean(CompanyRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(CompanyRepository.class).saveAndFlush(this);
    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(CompanyRepository.class).delete(this.id);

    }


    @Override
    public String getPartnerName() {
        return partnerName;
    }

    @Override
    public String getPartnerNature() {
        return partnerNature;
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
}
