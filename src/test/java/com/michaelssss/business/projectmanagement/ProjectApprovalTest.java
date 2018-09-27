package com.michaelssss.business.projectmanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.business.projectmanagement.impl.ApprovalImpl;
import com.michaelssss.business.projectmanagement.repository.ApprovalCatalog;
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
    private ApprovalCatalog approvalCatalog;

    public Approval getApproval() {
        Approval approval = ApprovalImpl.builder().billingWay("年化计费").billingMethod("全额结款")
                .credit(BigDecimal.valueOf(150)).creditRisk("中").creditType("1").deduction(BigDecimal.valueOf(3.00)).deductionPercentage("10")
                .effectiveTime("2018-7-21").interestRate("10").legalPerson("张三").logistics("空运").cashDepositWay("尾款抵扣").marketRisk("低")
                .meetTime(new Date()).minInterestDay("5").partnerName("测试").partnerId(1L).partnerMain("手机").partnerName("测试").productionId("手机")
                .projectName("测试项目").rate("10").build();
        return approval;
    }

    @Test
    public void addApprovalInfo() {
        Approval approval = getApproval();
        approval.addApproval();
        approvalCatalog.deleteById(((ApprovalImpl) approval).getId());
    }

    @Test
    public void updateApprovalInfo() {
        Approval approval = getApproval();
        approval.addApproval();
        ((ApprovalImpl) approval).setProjectName("修改测试");
        approval.updateApproval();
        ApprovalImpl approvalImpl = approvalCatalog.findById(((ApprovalImpl) approval).getId()).get();
        Assert.assertEquals("修改测试", approvalImpl.getProjectName());
        approvalCatalog.deleteById(((ApprovalImpl) approval).getId());
    }

    @Test
    public void permitProjectInfo() {
        Approval approval = getApproval();
        approval.addApproval();
        approval.confirm();
        ApprovalImpl approvalImpl = approvalCatalog.findById(((ApprovalImpl) approval).getId()).get();
        Assert.assertEquals(Approval.CONFIRM, approvalImpl.getState());
        approvalCatalog.deleteById(((ApprovalImpl) approval).getId());
    }
}
