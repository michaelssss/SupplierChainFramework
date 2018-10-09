package com.michaelssss.business.creditmanagement;

import java.math.BigDecimal;

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
   * 转让 如果转让不对票据做拆分，则转让记录的原始票据编码与新票据编码相同 拆分标准则是如果转让金额不是与票面金额不同则拆分
   *
   * @param companyName 转让公司名
   * @param amount 转让金额
   */
  void transfer(String companyName, BigDecimal amount);

  /**
   * 贴现
   */
  void disCount();
}
