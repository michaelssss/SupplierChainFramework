package com.michaelssss.business.riskmanagement;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/31 14:09
 */
public class EnterpriseCreditConstant {

  public static final String IDCARD_A = "无不良"; // 100
  public static final String IDCARD_B = "公安不良"; // 0

  public static final String WEBANK_A = "验证通过"; // 100
  public static final String WEBANK_B = "验证不通过"; // 0

  public static final String PEOPLEBANK_A = "还款记录未出现逾期或当前逾期M1"; // 100
  public static final String PEOPLEBANK_B = "还款记录出现当前逾期“M2”及以上或命中欺诈名单、风险名单"; // 0

  public static final String BLACKLIST_LEVEL_A = "未命中"; // 100
  public static final String BLACKLIST_LEVEL_B = "命中灰度账户"; // 20
  public static final String BLACKLIST_LEVEL_C = "命中关注账户"; // 50
  public static final String BLACKLIST_LEVEL_D = "高危账户"; // 0
  public static final String BLACKLIST_LEVEL_E = "命中极黑账户"; // 0

  public static final String LAWXP_A = "无诉讼记录"; // 100
  public static final String LAWXP_B = "有诉讼记录（业务纠纷）"; // 50
  public static final String LAWXP_C = "有诉讼记录（债权债务"; // 0

  public static final String ENTERPRISE_A = "正常经营"; // 100
  public static final String ENTERPRISE_B = "列入异常经营名单"; // 50
  public static final String ENTERPRISE_C = "营业执照注销或吊销"; // 0
}
