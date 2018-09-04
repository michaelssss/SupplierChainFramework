package com.michaelssss.rzzl2.projectmanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.rzzl2.projectmanagement.impl.ProjectInfo;
import com.michaelssss.rzzl2.projectmanagement.repository.ProjectInfoRepository;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
public class ProjectTest extends SpringBootTestBasic {
    @Autowired
    private ProjectInfoRepository repository;

    public Project getProject() {
        Project project = ProjectInfo.builder().dictProjectState("1").projectName("测试项目").customerAddress("测试地址").customerExpectAnalysis("风险低")
                .downstreamCompany("下游公司").guaranteeMark("").insuranceRatio("").interestRate("10").legalPerson("李四")
                .partnerCashDepositWay("ces").partnerExpectBillingMethod("").partnerExpectBillingWay("").partnerExpectCredit(BigDecimal.valueOf(3.00))
                .partnerExpectDeliveryCycle("").partnerExpectDeliveryLocal("深圳").partnerExpectGuaranteeForm("").partnerExpectRate("12")
                .partnerExpectTransport("").partnerId(2l).partnerMain("测试公司").partnerName("ces").productionName("手机").storageRequired("没有要求").build();
        return project;
    }

    @Test
    public void addProjectInfo() {
        Project project = getProject();
        project.addProjectInfo();
        project.deleteProjectInfo();
    }

    @Test
    public void updateProjectInfo() {
        Project project = getProject();
        ((ProjectInfo) project).setProjectName("测试修改项目");
        project.updateProjectInfo();
        ProjectInfo projectInfo = repository.findOne(((ProjectInfo) project).getId());
        Assert.assertEquals("测试修改项目", projectInfo.getProjectName());
        project.deleteProjectInfo();
    }

    @Test
    public void deleteProjectInfo() {
        Project project = getProject();
        project.addProjectInfo();
        project.deleteProjectInfo();
        ProjectInfo projectInfo = repository.findOne(((ProjectInfo) project).getId());
        Assert.assertNull(projectInfo);
    }

    @Test
    public void permitProjectInfo() {
        Project project = getProject();
        project.addProjectInfo();
        project.permit();
        ProjectInfo projectInfo = repository.findOne(((ProjectInfo) project).getId());
        Assert.assertEquals(Project.Approving, projectInfo.getDictProjectState());
        project.deleteProjectInfo();
    }

}
