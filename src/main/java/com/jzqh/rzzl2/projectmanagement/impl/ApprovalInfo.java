package com.jzqh.rzzl2.projectmanagement.impl;

import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.projectmanagement.Approval;
import com.jzqh.rzzl2.projectmanagement.repository.ApproalInfoRepository;
import com.jzqh.utils.BusinessCodeGenerator;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
@Builder
@Entity
@Data
@Table(name = "approval")
public class ApprovalInfo implements Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long partnerId;//合作伙伴id
    private Long projectId;//项目id
    private String projectName;//项目名称
    private String replyCode;//批复编号
    private String partnerName;//企业全称
    private String partnerMain;//合作主体
    private String supplier;//上游供应商
    private String partner;//下有合作伙伴
    private String production;//合作产品
    private BigDecimal creditLine;//授信额度
    private String effectiveTime;//授信额度有效期
    private String clearingForm;//结算方式
    private String operatingCycle;//运作周期
    private String securedForm;//担保形式
    private String rate;//费率
    private String chargeMode;//计费方式
    private String marginRatio;//保证金比例
    private String marginMethod;//保证金抵扣方式
    private String deductionPercentage;//每期抵扣比例
    private BigDecimal deduction;//每期抵扣金额
    private String dateChargeMode;//日期计算方式
    private String interestRate;//利息率
    private String minInterestDay;//最小利息计算天数
    private String marketRisk;//市场风险
    private String operationRisk;//操作风险
    private String creditRisk;//信用风险
    private Date meetTime;//上会时间
    private String tradeMode;//商流
    private String moneyStream;//资金流
    private String logistics;//物流
    private String creditType;//授信类型
    private String legalPerson;//法定代表
    private String registerAddress;//注册地址
    private String state;//状态

    @Override
    public void addProjectApprovalInfo() {
        SpringContextHolder.getBean(ApproalInfoRepository.class).saveAndFlush(this);
    }

    @Override
    public void updateProjectInfo() {
        SpringContextHolder.getBean(ApproalInfoRepository.class).saveAndFlush(this);
    }

    @Override
    public void deleteProjectInfo() {
        SpringContextHolder.getBean(ApproalInfoRepository.class).delete(this.id);
    }

    @Override
    public void confirm() {
        this.state = Approval.Confirm;
        SpringContextHolder.getBean(ApproalInfoRepository.class).saveAndFlush(this);
    }

    @Override
    public String getProjectApprovalCode() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "PF");
    }
}
