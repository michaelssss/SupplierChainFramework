package com.michaelssss.rzzl2.projectmanagement.impl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.BusinessException;
import com.michaelssss.rzzl2.exception.ExistException;
import com.michaelssss.rzzl2.projectmanagement.Project;
import com.michaelssss.rzzl2.projectmanagement.repository.ProjectCatalog;
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
    private Long id;
    @Column(length = 64, name = "project_name", unique = true)
    private String projectName;//项目名称
    private Long partnerId;//合作伙伴id
    private String partnerName;//企业全称
    private String partnerMain;//合作主体
    private String supplier;//上游供应商
    private String purchase;//下游合作伙伴
    private String productionId;//合作产品id
    private BigDecimal partnerExpectCredit;//合作伙伴期望授信额度
    private String partnerExpectRate;//合作伙伴期望费率
    private String partnerExpectBillingWay;//合作伙伴期望计费方式
    private String partnerExpectDeliveryCycle;//合作伙伴期望提货周期
    private String partnerExpectBillingMethod;//合作伙伴期望结算方式
    private String partnerExpectTransport;//合作伙伴期望运输费用承担方
    private String partnerExpectDeliveryLocal;//合作伙伴期望提货地点
    private String partnerExpectGuaranteeForm;//合作伙伴期望担保形式
    private String partnerExpectStorageRequired;//其他物流/仓储要求
    private String partnerExpectCashDepositRatio;//期望保证金比例
    private String partnerExpectDepositWay;//期望保证金抵扣方式
    private String partnerExpectAnalysis;//合作伙伴需求合理性分析
    private String guaranteeMark;//担保备注
    private String interestRate;//利息率
    private String legalPerson;//法定代表
    private String registerAddress;//注册地址
    private String state;//项目状态


    @Override
    public void addProject() {
        ProjectImpl project = ProjectImpl.builder().build();
        project.setProjectName(this.projectName);
        Example<ProjectImpl> ex = Example.of(project);
        if (SpringContextHolder.getBean(ProjectCatalog.class).exists(ex)) {
            throw new ExistException("项目名称已存在");
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
