package com.michaelssss.business.creditmanagement;

public interface CreditService {

  /**
   * 获取某公司当前的额度
   *
   * @param companyFullName 某公司
   */
  Credit getCompanyCredit(String companyFullName);
}
