package com.michaelssss.rzzl2.projectmanagement.impl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.BusinessException;
import com.michaelssss.rzzl2.projectmanagement.Approval;
import com.michaelssss.rzzl2.projectmanagement.repository.ApprovalCatalog;
import com.michaelssss.utils.BusinessCodeGenerator;
import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty(value = "唯一ID")
    private Long id;
    @Column(length = 64, name = "reply_code", unique = true)
    @ApiModelProperty(value = "批复编号")
    private String replyCode;
    @ApiModelProperty(value = "合作伙伴id")
    private Long partnerId;
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "企业全称")
    private String partnerName;
    @ApiModelProperty(value = "合作主体")
    private String partnerMain;
    @ApiModelProperty(value = "上游供应商")
    private String supplier;
    @ApiModelProperty(value = "下游合作伙伴")
    private String purchase;
    @ApiModelProperty(value = "合作产品id")
    private String productionId;
    @ApiModelProperty(value = "授信额度")
    private BigDecimal credit;
    @ApiModelProperty(value = "费率")
    private String rate;
    @ApiModelProperty(value = "计费方式")
    private String billingWay;
    @ApiModelProperty(value = "提货周期")
    private String deliveryCycle;
    @ApiModelProperty(value = "结算方式")
    private String billingMethod;
    @ApiModelProperty(value = "运输费用承担方")
    private String transport;
    @ApiModelProperty(value = "提货地点")
    private String deliveryLocal;
    @ApiModelProperty(value = "担保形式")
    private String guaranteeForm;
    @ApiModelProperty(value = "保证金比例")
    private String cashDepositRatio;
    @ApiModelProperty(value = "保证金抵扣方式")
    private String cashDepositWay;
    @ApiModelProperty(value = "每期抵扣比例")
    private String deductionPercentage;
    @ApiModelProperty(value = "每期抵扣金额")
    private BigDecimal deduction;
    @ApiModelProperty(value = "授信额度有效期")
    private String effectiveTime;
    @ApiModelProperty(value = "利息率")
    private String interestRate;
    @ApiModelProperty(value = "法定代表")
    private String legalPerson;
    @ApiModelProperty(value = "注册地址")
    private String registerAddress;
    @ApiModelProperty(value = "最小利息计算天数")
    private String minInterestDay;
    @ApiModelProperty(value = "市场风险")
    private String marketRisk;
    @ApiModelProperty(value = "操作风险")
    private String operationRisk;
    @ApiModelProperty(value = "信用风险")
    private String creditRisk;
    @ApiModelProperty(value = "上会时间")
    private Date meetTime;
    @ApiModelProperty(value = "商流")
    private String tradeMode;
    @ApiModelProperty(value = "资金流")
    private String moneyStream;
    @ApiModelProperty(value = "物流")
    private String logistics;
    @ApiModelProperty(value = "授信类型")
    private String creditType;
    @ApiModelProperty(value = "状态", hidden = true)
    private String state;

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
