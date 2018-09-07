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
    private long id;
    @Column(length = 64, name = "project_name")
    private String projectName;//项目名称
    private Long partnerId;//合作伙伴id
    private String partnerName;//企业全称
    private String partnerMain;//合作伙伴主体
    private String upstreamCompany;//上游供应商
    private String downstreamCompany;//下游合作伙伴
    private Long productionId;//合作产品id
    private String productionName;//合作产品
    private BigDecimal partnerExpectCredit;//合作伙伴期望授信额度
    private String partnerExpectRate;//合作伙伴期望费率
    private String partnerExpectBillingWay;//合作伙伴期望计费方式
    private String partnerExpectDeliveryCycle;//合作伙伴期望提货周期
    private String partnerExpectBillingMethod;//合作伙伴期望结算方式
    private String partnerExpectTransport;//合作伙伴期望运输费用承担方
    private String partnerExpectDeliveryLocal;//合作伙伴期望提货地点
    private String partnerExpectGuaranteeForm;//合作伙伴期望担保形式
    private String storageRequired;//其他物流/仓储要求
    private String insuranceRatio;//保证金比例
    private String state;//项目状态
    private String customerExpectAnalysis;//合作伙伴需求合理性分析
    private String partnerCashDepositWay;//合作伙伴期望保证金抵扣方式
    private String guaranteeMark;//担保备注
    private String interestRate;//利息率
    private String legalPerson;//法定代表
    private String customerAddress;//注册地址


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
