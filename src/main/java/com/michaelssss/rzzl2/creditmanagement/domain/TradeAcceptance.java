package com.michaelssss.rzzl2.creditmanagement.domain;

import com.michaelssss.rzzl2.creditmanagement.Acceptance;

/**
 * 商业承兑汇票
 */
public class TradeAcceptance implements Acceptance {
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
