package com.michaelssss.business.creditmanagement;

import java.math.BigDecimal;

public interface Credit {
    /**
     * 提高额度
     *
     * @param credit
     */
    void limitIncrease(Credit credit);

    /**
     * 降低额度
     *
     * @param credit
     */
    void limitDecrease(Credit credit);

    /**
     * 获取额度的值
     *
     * @return
     */
    BigDecimal getCreditLimitValue();

    /**
     * 获取可用额度
     * 可用额度一定少于额度
     *
     * @return
     */
    BigDecimal getCreditRemainValue();

    /**
     * 获取额度所在公司公司名
     *
     * @return
     */
    String getCreditCompany();

    /**
     * 使用额度
     *
     * @param creditValue 需要使用的额度大小
     * @throws com.michaelssss.business.BusinessException 若用额超过了剩余额度，则抛出用额超出限制的错误
     */
    void useCredit(BigDecimal creditValue, String code);
}
