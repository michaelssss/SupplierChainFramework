package com.michaelssss.business.projectmanagement;

import com.michaelssss.SpringBootTestBasic;
import com.michaelssss.business.projectmanagement.impl.ProjectImpl;
import com.michaelssss.business.projectmanagement.repository.ProjectCatalog;
import java.math.BigDecimal;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;

/**
 * @Description: @Author:tanshaoxing @Date:2018/7/16
 */
public class ProjectTest extends SpringBootTestBasic {

  @Autowired
  private ProjectCatalog repository;

  public Project getProject() {
    Project project =
        ProjectImpl.builder()
            .projectName("测试项目")
            .registerAddress("测试地址")
            .partnerExpectAnalysis("风险低")
            .purchase("下游公司")
            .guaranteeMark("")
            .partnerExpectRate("")
            .interestRate("10")
            .legalPerson("李四")
            .partnerExpectCashDepositRatio("ces")
            .partnerExpectBillingMethod("")
            .partnerExpectBillingWay("")
            .partnerExpectCredit(BigDecimal.valueOf(3.00))
            .partnerExpectDeliveryCycle("")
            .partnerExpectDeliveryLocal("深圳")
            .partnerExpectGuaranteeForm("")
            .partnerExpectRate("12")
            .partnerExpectTransport("")
            .partnerId(2l)
            .partnerMain("测试公司")
            .partnerName("ces")
            .productionId("手机")
            .partnerExpectStorageRequired("没有要求")
            .state("EDITABLE")
            .build();
    return project;
  }

  @Test
  public void addProjectInfo() {
    Project project = getProject();
    project.addProject();
    repository.delete(repository.findOne(Example.of((ProjectImpl) project)).get());
  }

  @Test
  public void updateProjectInfo() {
    Project project = getProject();
    ((ProjectImpl) project).setProjectName("测试修改项目");
    project.updateProject();
    ProjectImpl projectImpl = repository.findById(((ProjectImpl) project).getId()).get();
    Assert.assertEquals("测试修改项目", projectImpl.getProjectName());
    repository.delete(repository.findOne(Example.of((ProjectImpl) project)).get());
  }

  @Test
  public void deleteProjectInfo() {
    Project project = getProject();
    project.addProject();
    repository.delete(repository.findOne(Example.of((ProjectImpl) project)).get());
    Assert.assertTrue(!repository.findById(((ProjectImpl) project).getId()).isPresent());
  }

  @Test
  public void permitProjectInfo() {
    Project project = getProject();
    project.addProject();
    project.apply();
    ProjectImpl projectImpl = repository.findById(((ProjectImpl) project).getId()).get();
    Assert.assertEquals(Project.APPROVING, projectImpl.getState());
    repository.delete(repository.findOne(Example.of((ProjectImpl) project)).get());
  }
}
