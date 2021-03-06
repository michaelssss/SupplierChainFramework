package com.michaelssss.business.basicinfomanagement.service;

import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.domain.Address;
import com.michaelssss.business.basicinfomanagement.domain.BankAccount;
import com.michaelssss.business.basicinfomanagement.domain.CompanyImpl;
import com.michaelssss.business.basicinfomanagement.domain.Contact;
import com.michaelssss.business.basicinfomanagement.domain.ShareholderInfo;
import com.michaelssss.business.basicinfomanagement.respository.CompanyRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
public class CompanyHistoryService {

  @Autowired
  private CompanyRepository companyRepository;

  public Company getSpecialCompanyHistoryByHistoryIdAndCompanyName(
      String companyName, String historyId) {
    // 这是数据库唯一索引保证一定要么有且只有一条，要么一条没有
    CompanyImpl sample =
        CompanyImpl.builder().companyName(companyName).historyId(historyId).build();
    return companyRepository.findOne(Example.of(sample)).get();
  }

  public Company getLatestCompany(String companyName) {
    // 这是数据库唯一索引保证一定要么有且只有一条，要么一条没有
    CompanyImpl sample = CompanyImpl.builder().companyName(companyName).build();
    List<CompanyImpl> companies = companyRepository.findAll(Example.of(sample));
    companies.sort(
        (c1, c2) ->
            Long.compare(Long.parseLong(c1.getHistoryId()), Long.parseLong(c2.getHistoryId())));
    return companies.size() == 0 ? null : companies.get(0);
  }

  public List<Company> getCompanyAllAuditHistory(String companyName) {
    CompanyImpl sample = CompanyImpl.builder().companyName(companyName).build();
    List<CompanyImpl> companies = companyRepository.findAll(Example.of(sample));
    companies.sort(
        (o1, o2) ->
            Long.compare(Long.parseLong(o1.getHistoryId()), Long.parseLong(o2.getHistoryId())));
    return new ArrayList<>(companies);
  }

  public List<Company> getAllCompanyLatestHistory(Company sample) {
    List<CompanyImpl> companies = companyRepository.findAll(Example.of((CompanyImpl) sample));
    List<Company> companyList = new ArrayList<>();
    Map<String, List<Company>> companyMap = new HashMap<>();
    for (Company company : companies) {
      if (!companyMap.containsKey(company.getCompanyName())) {
        List<Company> companyList1 = new ArrayList<>();
        companyList1.add(company);
        companyMap.put(company.getCompanyName(), companyList1);
      } else {
        companyMap.get(company.getCompanyName()).add(company);
      }
    }
    for (Map.Entry<String, List<Company>> entry : companyMap.entrySet()) {
      entry
          .getValue()
          .sort(
              (o1, o2) -> {
                return Long.compare(
                    Long.valueOf(o1.getHistoryId()), Long.valueOf(o2.getHistoryId()));
              });
      companyList.add(entry.getValue().get(0));
    }
    return companyList;
  }

  /**
   * 需要对company对象做深拷贝，否则与预期不符
   *
   * @param company 需要做深拷贝的公司对象
   * @return 拷贝后的对象
   */
  @Transactional(rollbackOn = Exception.class)
  public Company addNewRecord(Company company) {
    CompanyImpl company1 = (CompanyImpl) company;
    CompanyImpl company2 = CompanyImpl.builder().build();
    BeanUtils.copyProperties(company1, company2);
    company2.setId(null);
    company2.setHistoryId(Long.toString(System.currentTimeMillis()));
    Set<Address> addresses = new HashSet<>();
    Set<ShareholderInfo> shareholderInfos = new HashSet<>();
    Set<BankAccount> bankAccounts = new HashSet<>();
    Set<Contact> contacts = new HashSet<>();
    for (Address address : company2.getAddressSet()) {
      Address address1 = new Address();
      BeanUtils.copyProperties(address, address1, "id");
      addresses.add(address1);
    }
    for (ShareholderInfo shareholderInfo : company2.getShareholderInfoSet()) {
      ShareholderInfo shareholderInfo1 = new ShareholderInfo();
      BeanUtils.copyProperties(shareholderInfo, shareholderInfo1, "id");
      shareholderInfos.add(shareholderInfo1);
    }
    for (BankAccount bankAccount : company2.getBankAccounts()) {
      BankAccount bankAccount1 = new BankAccount();
      BeanUtils.copyProperties(bankAccount, bankAccount1, "id");
      bankAccounts.add(bankAccount1);
    }
    for (Contact contact : company2.getContactSet()) {
      Contact contact1 = new Contact();
      BeanUtils.copyProperties(contact, contact1, "id");
      contacts.add(contact1);
    }
    company2.setAddressSet(addresses);
    company2.setShareholderInfoSet(shareholderInfos);
    company2.setContactSet(contacts);
    company2.setBankAccounts(bankAccounts);
    return companyRepository.saveAndFlush(company2);
  }
}
