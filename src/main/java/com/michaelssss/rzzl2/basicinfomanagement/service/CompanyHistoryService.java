package com.michaelssss.rzzl2.basicinfomanagement.service;

import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.respository.CompanyRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class CompanyHistoryService {
    @Autowired
    private CompanyRepository companyRepository;

    public Company getSpecialCompanyHistoryByHistoryIdAndCompanyName(String companyName, String historyId) {
        //这是数据库唯一索引保证一定要么有且只有一条，要么一条没有
        CompanyImpl sample = CompanyImpl.builder().partnerName(companyName).historyId(historyId).build();
        return companyRepository.findOne(Example.of(sample));
    }

    public Company getLatestCompany(String companyName) {
        //这是数据库唯一索引保证一定要么有且只有一条，要么一条没有
        CompanyImpl sample = CompanyImpl.builder().partnerName(companyName).build();
        List<CompanyImpl> companies = companyRepository.findAll(Example.of(sample));
        companies.sort((c1, c2) -> Long.compare(Long.parseLong(c1.getHistoryId()), Long.parseLong(c2.getHistoryId())));
        return companies.size() == 0 ? null : companies.get(0);
    }

    public List<Company> getCompanyAllAuditHistory(String companyName) {
        CompanyImpl sample = CompanyImpl.builder().partnerName(companyName).build();
        List<CompanyImpl> companies = companyRepository.findAll(Example.of(sample));
        companies.sort((o1, o2) -> Long.compare(Long.parseLong(o1.getHistoryId()), Long.parseLong(o2.getHistoryId())));
        return new ArrayList<>(companies);
    }

    public List<Company> getAllCompanyLatestHistory() {
        List<CompanyImpl> companies = companyRepository.findAll();
        List<Company> companyList = new ArrayList<>();
        Map<String, List<Company>> companyMap = new HashMap<>();
        for (Company company : companies) {
            if (companyMap.containsKey(company.getPartnerName())) {
                List<Company> companyList1 = new ArrayList<>();
                companyList1.add(company);
                companyMap.put(company.getPartnerName(), companyList1);
            } else {
                companyMap.get(company.getPartnerName()).add(company);
            }
        }
        for (Map.Entry<String, List<Company>> entry : companyMap.entrySet()) {
            entry.getValue().sort((o1, o2) -> {
                return Long.compare(Long.valueOf(o1.getHistoryId()), Long.valueOf(o2.getHistoryId()));
            });
            companyList.add(entry.getValue().get(0));
        }
        return companyList;
    }

    public Company addNewRecord(Company company) {
        CompanyImpl company1 = (CompanyImpl) company;
        CompanyImpl company2 = CompanyImpl.builder().build();
        BeanUtils.copyProperties(company1, company2);
        company2.setId(null);
        company2.setHistoryId(Long.toString(new Date().getTime()));
        return companyRepository.saveAndFlush(company2);
    }
}
