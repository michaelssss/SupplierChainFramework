package com.michaelssss.business.basicinfomanagement.service;

import com.michaelssss.business.basicinfomanagement.Company;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CompanyService {

  private CompanyHistoryService companyHistoryService;

  @Autowired
  public CompanyService(CompanyHistoryService companyHistoryService) {
    this.companyHistoryService = companyHistoryService;
  }

  @Transactional(rollbackOn = Exception.class)
  public void startApplyAudit(String companyName, String historyId) {
    Company company =
        this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    company.applyAudit();
  }
}
