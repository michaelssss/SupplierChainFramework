package com.michaelssss.bpm;

import com.michaelssss.account.User;
import com.michaelssss.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

@Controller
@Slf4j
@Api(value = "BPMTest")
@RequestMapping(value = "BPMService")
public class BPMService {

  @Autowired
  private ProcessEngine processEngine;

  @RequestMapping(value = "Task/start")
  @ApiOperation(value = "testStartTask")
  @ResponseBody
  public Response startTask(@SessionAttribute("user") User user) {
    List list = processEngine.getRepositoryService().createDeploymentQuery().list();
    ProcessInstance processInstance =
        processEngine.getRuntimeService().startProcessInstanceByKey("HelloWorld");
    Task helloWorldTask =
        processEngine
            .getTaskService()
            .createTaskQuery()
            .processInstanceId(processInstance.getId())
            .singleResult();
    processEngine.getTaskService().setAssignee(helloWorldTask.getId(), user.getUsername());
    return Response.OK("start Tasked");
  }


}
