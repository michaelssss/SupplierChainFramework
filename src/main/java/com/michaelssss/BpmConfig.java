package com.michaelssss;

import javax.sql.DataSource;
import org.activiti.engine.DynamicBpmnService;
import org.activiti.engine.FormService;
import org.activiti.engine.HistoryService;
import org.activiti.engine.IdentityService;
import org.activiti.engine.ManagementService;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.ProcessEngineConfiguration;
import org.activiti.engine.RepositoryService;
import org.activiti.engine.RuntimeService;
import org.activiti.engine.TaskService;
import org.activiti.engine.impl.cfg.ProcessEngineConfigurationImpl;
import org.activiti.spring.ProcessEngineFactoryBean;
import org.activiti.spring.SpringProcessEngineConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BpmConfig {

  // 流程配置，与spring整合采用SpringProcessEngineConfiguration这个实现
  @Bean
  public ProcessEngineConfiguration processEngineConfiguration(
      DataSource dataSource,
      PlatformTransactionManager transactionManager,
      ResourceLoader resourceLoader) {
    SpringProcessEngineConfiguration processEngineConfiguration =
        new SpringProcessEngineConfiguration();
    processEngineConfiguration.setDataSource(dataSource);
    processEngineConfiguration.setDatabaseSchemaUpdate(
        SpringProcessEngineConfiguration.DB_SCHEMA_UPDATE_TRUE);
    processEngineConfiguration.setDatabaseType(
        SpringProcessEngineConfiguration.DATABASE_TYPE_MYSQL);
    processEngineConfiguration.setTransactionManager(transactionManager);
    processEngineConfiguration.setDeploymentResources(
        new Resource[]{resourceLoader.getResource("classpath:/processes/HelloWorld.bpmn")});
    return processEngineConfiguration;
  } // 流程引擎，与spring整合使用factoryBean

  @Bean
  public ProcessEngineFactoryBean processEngine(
      ProcessEngineConfiguration processEngineConfiguration) {
    ProcessEngineFactoryBean processEngineFactoryBean = new ProcessEngineFactoryBean();
    processEngineFactoryBean.setProcessEngineConfiguration(
        (ProcessEngineConfigurationImpl) processEngineConfiguration);
    return processEngineFactoryBean;
  } // 八大接口

  @Bean
  public RepositoryService repositoryService(ProcessEngine processEngine) {
    return processEngine.getRepositoryService();
  }

  @Bean
  public RuntimeService runtimeService(ProcessEngine processEngine) {
    return processEngine.getRuntimeService();
  }

  @Bean
  public TaskService taskService(ProcessEngine processEngine) {
    return processEngine.getTaskService();
  }

  @Bean
  public HistoryService historyService(ProcessEngine processEngine) {
    return processEngine.getHistoryService();
  }

  @Bean
  public FormService formService(ProcessEngine processEngine) {
    return processEngine.getFormService();
  }

  @Bean
  public IdentityService identityService(ProcessEngine processEngine) {
    return processEngine.getIdentityService();
  }

  @Bean
  public ManagementService managementService(ProcessEngine processEngine) {
    return processEngine.getManagementService();
  }

  @Bean
  public DynamicBpmnService dynamicBpmnService(ProcessEngine processEngine) {
    return processEngine.getDynamicBpmnService();
  } // 八大接口 end
}
