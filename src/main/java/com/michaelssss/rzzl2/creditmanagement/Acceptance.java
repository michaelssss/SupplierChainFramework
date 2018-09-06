package com.michaelssss.rzzl2.creditmanagement;

/**
 * 承兑汇票
 *
 * @author Michaelssss
 * @version 1.0
 */
public interface Acceptance {
    /**
     * 开票
     */
    Acceptance apply();

    /**
     * 转让
     */
    void transfer(String companyName);

    /**
     * 贴现
     */
    void disCount();

}
