package com.jzqh.rzzl2.projectmanagement;

import com.jzqh.SpringBootTestBasic;
import com.jzqh.rzzl2.projectmanagement.impl.ApprovalInfo;
import com.jzqh.rzzl2.projectmanagement.repository.ApproalInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */

public class ProjectApprovalTest extends SpringBootTestBasic {

    @Autowired
    private ApproalInfoRepository approalInfoRepository;

    public Approval getApproval() {
        Approval approval = ApprovalInfo.builder().chargeMode("年化计费").clearingForm("全额结款")
                .creditLine(BigDecimal.valueOf(150)).creditRisk("中").creditType("1").dateChargeMode("dd").deduction(BigDecimal.valueOf(3.00)).deductionPercentage("10")
                .effectiveTime("2018-7-21").interestRate("10").legalPerson("张三").logistics("空运").marginMethod("尾款抵扣").marketRisk("低")
                .meetTime(new Date()).minInterestDay("5").partner("测试").partnerId(1L).partnerMain("手机").partnerName("测试").production("手机")
                .projectName("测试项目").rate("10").build();
        return approval;
    }

    @Test
    public void addApprovalInfo() {
        Approval approval = getApproval();
        approval.addProjectApprovalInfo();
        approval.deleteProjectInfo();
    }

    @Test
    public void updateApprovalInfo() {
        Approval approval = getApproval();
        approval.addProjectApprovalInfo();
        ((ApprovalInfo) approval).setProjectName("修改测试");
        approval.updateProjectInfo();
        ApprovalInfo approvalInfo = approalInfoRepository.findOne(((ApprovalInfo) approval).getId());
        Assert.assertEquals("修改测试", approvalInfo.getProjectName());
        approval.deleteProjectInfo();
    }

    @Test
    public void permitProjectInfo() {
        Approval approval = getApproval();
        approval.addProjectApprovalInfo();
        approval.confirm();
        ApprovalInfo approvalInfo = approalInfoRepository.findOne(((ApprovalInfo) approval).getId());
        Assert.assertEquals(Approval.Confirm, approvalInfo.getState());
        approval.deleteProjectInfo();
    }


}
