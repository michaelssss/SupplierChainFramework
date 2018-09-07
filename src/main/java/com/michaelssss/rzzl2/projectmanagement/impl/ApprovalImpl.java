package com.michaelssss.rzzl2.projectmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.BusinessException;
import com.michaelssss.rzzl2.projectmanagement.Approval;
import com.michaelssss.rzzl2.projectmanagement.repository.ApprovalCatalog;
import com.michaelssss.utils.BusinessCodeGenerator;
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
public class ApprovalImpl implements Approval {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 64, name = "reply_code", unique = true)
    private String replyCode;//批复编号
    private Long partnerId;//合作伙伴id
    private String projectName;//项目名称
    private String partnerName;//企业全称
    private String partnerMain;//合作主体
    private String supplier;//上游供应商
    private String purchase;//下游合作伙伴
    private String productionId;//合作产品id
    private BigDecimal credit;//授信额度
    private String rate;//费率
    private String billingWay;//计费方式
    private String deliveryCycle;//提货周期
    private String billingMethod;//结算方式
    private String transport;//运输费用承担方
    private String deliveryLocal;//提货地点
    private String guaranteeForm;//担保形式
    private String cashDepositRatio;//保证金比例
    private String cashDepositWay;//保证金抵扣方式
    private String deductionPercentage;//每期抵扣比例
    private BigDecimal deduction;//每期抵扣金额
    private String effectiveTime;//授信额度有效期
    private String interestRate;//利息率
    private String legalPerson;//法定代表
    private String registerAddress;//注册地址
    private String minInterestDay;//最小利息计算天数
    private String marketRisk;//市场风险
    private String operationRisk;//操作风险
    private String creditRisk;//信用风险
    private Date meetTime;//上会时间
    private String tradeMode;//商流
    private String moneyStream;//资金流
    private String logistics;//物流
    private String creditType;//授信类型
    private String state;//状态

    @Override
    public void addApproval() {
        this.replyCode = getProjectApprovalCode();
        this.state = EDITABLE;
        SpringContextHolder.getBean(ApprovalCatalog.class).saveAndFlush(this);
    }

    @Override
    public void updateApproval() {
        if (!this.state.equals(EDITABLE)) {
            throw new BusinessException("本条批复已确认生效，不可修改");
        }
        SpringContextHolder.getBean(ApprovalCatalog.class).saveAndFlush(this);
    }

    @Override
    public void confirm() {
        this.state = Approval.CONFIRM;
        SpringContextHolder.getBean(ApprovalCatalog.class).saveAndFlush(this);
    }

    private String getProjectApprovalCode() {
        return SpringContextHolder.
                getBean(BusinessCodeGenerator.class).
                getSequence(this.getClass(), "PF");
    }
}
