package com.michaelssss.business.projectmanagement.impl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.projectmanagement.Project;
import com.michaelssss.business.projectmanagement.repository.ProjectCatalog;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;
import org.springframework.data.domain.Example;

import javax.persistence.*;
import java.math.BigDecimal;

@Builder
@Entity
@Data
@Table(name = "project", indexes = {
        @Index(name = "idx_projectname", columnList = "project_name", unique = true)
})
public class ProjectImpl implements Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @ApiModelProperty(value = "唯一ID")
    private Long id;
    @Column(length = 64, name = "project_name", unique = true)
    @ApiModelProperty(value = "项目名称")
    private String projectName;
    @ApiModelProperty(value = "合作伙伴id")
    private Long partnerId;
    @ApiModelProperty(value = "选择一个版本信息")
    private String historyId;
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
    @ApiModelProperty(value = "合作伙伴期望授信额度")
    private BigDecimal partnerExpectCredit;
    @ApiModelProperty(value = "合作伙伴期望费率")
    private String partnerExpectRate;
    @ApiModelProperty(value = "合作伙伴期望计费方式")
    private String partnerExpectBillingWay;
    @ApiModelProperty(value = "合作伙伴期望提货周期")
    private String partnerExpectDeliveryCycle;
    @ApiModelProperty(value = "合作伙伴期望结算方式")
    private String partnerExpectBillingMethod;
    @ApiModelProperty(value = "合作伙伴期望运输费用承担方")
    private String partnerExpectTransport;
    @ApiModelProperty(value = "合作伙伴期望提货地点")
    private String partnerExpectDeliveryLocal;
    @ApiModelProperty(value = "合作伙伴期望担保形式")
    private String partnerExpectGuaranteeForm;
    @ApiModelProperty(value = "其他物流/仓储要求")
    private String partnerExpectStorageRequired;
    @ApiModelProperty(value = "期望保证金比例")
    private String partnerExpectCashDepositRatio;
    @ApiModelProperty(value = "期望保证金抵扣方式")
    private String partnerExpectDepositWay;
    @ApiModelProperty(value = "合作伙伴需求合理性分析")
    private String partnerExpectAnalysis;
    @ApiModelProperty(value = "担保备注")
    private String guaranteeMark;
    @ApiModelProperty(value = "利息率")
    private String interestRate;
    @ApiModelProperty(value = "法定代表")
    private String legalPerson;
    @ApiModelProperty(value = "注册地址")
    private String registerAddress;
    @ApiModelProperty(value = "项目状态")
    private String state;


    @Override
    public void addProject() {
        ProjectImpl project = ProjectImpl.builder().build();
        project.setProjectName(this.projectName);
        Example<ProjectImpl> ex = Example.of(project);
        if (SpringContextHolder.getBean(ProjectCatalog.class).exists(ex)) {
            throw new BusinessException("项目名称已存在");
        }
        this.state = EDITABLE;
        SpringContextHolder.getBean(ProjectCatalog.class).saveAndFlush(this);
    }

    @Override
    public void updateProject() {
        if (state.equals(EDITABLE)) {
            SpringContextHolder.getBean(ProjectCatalog.class).saveAndFlush(this);
        } else {
            throw new BusinessException("项目已经提交审批或者已经审批结束");
        }
    }

    @Override
    public void apply() {
        this.setState(Project.APPROVING);
        SpringContextHolder.getBean(ProjectCatalog.class).saveAndFlush(this);
    }
}
