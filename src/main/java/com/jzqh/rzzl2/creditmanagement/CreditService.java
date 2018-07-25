package com.jzqh.rzzl2.creditmanagement;


public interface CreditService {

    /**
     * 获取某公司当前的额度
     *
     * @param companyFullName 某公司
     * @return
     */
    Credit getCompanyCredit(String companyFullName);
}
