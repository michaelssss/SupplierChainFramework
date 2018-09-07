package com.michaelssss.rzzl2.creditmanagement.domain;

import com.michaelssss.rzzl2.creditmanagement.Acceptance;

/**
 * 银行承兑汇票
 */
public class BankAcceptance implements Acceptance {
    @Override
    public Acceptance apply() {
        return null;
    }

    @Override
    public void transfer(String companyName) {

    }

    @Override
    public void disCount() {

    }
}
