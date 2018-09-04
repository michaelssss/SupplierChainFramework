package com.michaelssss.rzzl2.basicinfomanagement.service;

import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.CompanyAuditHistoryEntity;
import com.michaelssss.rzzl2.basicinfomanagement.respository.CompanyHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyHistoryQuery {
    @Autowired
    private CompanyHistoryRepository companyHistoryRepository;

    public Company getSpecialCompanyHistoryByHistoryIdAndCompanyName(String companyName, String historyId) {
        CompanyAuditHistoryEntity companyAuditHistoryEntity = new CompanyAuditHistoryEntity();
        companyAuditHistoryEntity.setHistoryId(historyId);
        companyAuditHistoryEntity.setCompanyName(companyName);
        return companyHistoryRepository.findOne(Example.of(companyAuditHistoryEntity)).getCompany();
    }

    public List<Company> getCompanyAllAuditHistory(String companyName) {
        List<Company> companies = new ArrayList<>();
        CompanyAuditHistoryEntity companyAuditHistoryEntity = new CompanyAuditHistoryEntity();
        companyAuditHistoryEntity.setCompanyName(companyName);
        List<CompanyAuditHistoryEntity> companyAuditHistoryEntities = companyHistoryRepository.findAll(Example.of(companyAuditHistoryEntity));
        for (CompanyAuditHistoryEntity companyAuditHistoryEntity1 : companyAuditHistoryEntities) {
            companies.add(companyAuditHistoryEntity1.getCompany());
        }
        return companies;
    }
}
