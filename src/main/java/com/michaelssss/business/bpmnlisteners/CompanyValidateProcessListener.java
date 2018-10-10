package com.michaelssss.business.bpmnlisteners;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.service.CompanyHistoryService;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.delegate.DelegateTask;
import org.activiti.engine.delegate.TaskListener;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

@Slf4j
public class CompanyValidateProcessListener implements TaskListener {

  @Override
  public void notify(DelegateTask delegateTask) {
    RuntimeService runtimeService = SpringContextHolder.getBean(RuntimeService.class);
    String companyName =
        runtimeService.getVariable(delegateTask.getExecutionId(), "companyName", String.class);
    String historyId =
        runtimeService.getVariable(delegateTask.getExecutionId(), "historyId", String.class);
    PlatformTransactionManager platformTransactionManager =
        SpringContextHolder.getBean(PlatformTransactionManager.class);
    TransactionStatus transactionStatus =
        platformTransactionManager.getTransaction(new DefaultTransactionDefinition());
    CompanyHistoryService companyHistoryService =
        SpringContextHolder.getBean(CompanyHistoryService.class);
    try {
      Company company =
          companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
              companyName, historyId);
      company.validated();
      platformTransactionManager.commit(transactionStatus);
    } catch (Exception e) {
      log.error("complete validate companyInfo failed, because" + e);
      platformTransactionManager.rollback(transactionStatus);
    }
  }
}
