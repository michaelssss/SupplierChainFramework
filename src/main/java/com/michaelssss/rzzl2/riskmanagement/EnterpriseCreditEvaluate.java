package com.michaelssss.rzzl2.riskmanagement;

import lombok.Data;

import javax.persistence.Entity;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 企业信用评估
 * @author Tongch
 * @version 1.0
 * @time 2018/7/31 11:16
 */
@Entity
@Data
public class EnterpriseCreditEvaluate extends AbstractEvaluate{
    
    @NotNull
    private String idCard;//身份证公安验证
    
    @NotNull
    private String weBank;//微众银行人脸识别
    
    @NotNull
    private String peopleBank;//人行征信数据
    
    @NotNull
    private String blackList;//黑名单列表
    
    @NotNull
    private String lawXp;//汇法网
    
    @NotNull
    private String enterprise;//企信网征信
    
    @NotNull
    private Date evaluateDate;//评分日期
    
    @Override
    public BigDecimal eval() {
        return null;
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param idCard
     * @return
     */
    public int evaluteIdCard(String idCard){
        switch (idCard) {
            case EnterpriseCreditConstant.IDCARD_A:
                return 100;
            case EnterpriseCreditConstant.IDCARD_B:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param weBank
     * @return
     */
    public int evaluteWebank(String weBank){
        switch (idCard) {
            case EnterpriseCreditConstant.WEBANK_A:
                return 100;
            case EnterpriseCreditConstant.WEBANK_B:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param peopleBank
     * @return
     */
    public int evalutePeopleBank(String peopleBank){
        switch (peopleBank) {
            case EnterpriseCreditConstant.PEOPLEBANK_A:
                return 100;
            case EnterpriseCreditConstant.PEOPLEBANK_B:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param blackList
     * @return
     */
    public int evaluteBlackList(String blackList){
        switch (blackList) {
            case EnterpriseCreditConstant.BLACKLIST_LEVEL_A:
                return 100;
            case EnterpriseCreditConstant.BLACKLIST_LEVEL_B:
                return 20;
            case EnterpriseCreditConstant.BLACKLIST_LEVEL_C:
                return 50;
            case EnterpriseCreditConstant.BLACKLIST_LEVEL_D:
                return 0;
            case EnterpriseCreditConstant.BLACKLIST_LEVEL_E:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param lawXp
     * @return
     */
    public int evaluteLawXp(String lawXp){
        switch (lawXp) {
            case EnterpriseCreditConstant.LAWXP_A:
                return 100;
            case EnterpriseCreditConstant.LAWXP_B:
                return 50;
            case EnterpriseCreditConstant.LAWXP_C:
                return 0;
            default:
                return -1;
        }
    }
    
    /**
     * 根据String返回对应的值，异常时返回-1
     * @param enterprise
     * @return
     */
    public int evaluteEnterprise(String enterprise){
        switch (enterprise) {
            case EnterpriseCreditConstant.ENTERPRISE_A:
                return 100;
            case EnterpriseCreditConstant.ENTERPRISE_B:
                return 50;
            case EnterpriseCreditConstant.ENTERPRISE_C:
                return 0;
            default:
                return -1;
        }
    }
    
}
