package com.michaelssss.bpm;

import com.michaelssss.account.User;
import com.michaelssss.base.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        ProcessInstance processInstance = processEngine.getRuntimeService().startProcessInstanceByKey("HelloWorld");
        Task helloWorldTask = processEngine.getTaskService().createTaskQuery().processInstanceId(processInstance.getId()).singleResult();
        processEngine.getTaskService().setAssignee(helloWorldTask.getId(), user.getUsername());
        return Response.OK("start Tasked");
    }

    @RequestMapping(value = "Task/query")
    @ApiOperation(value = "testQueryTask")
    @ResponseBody
    public Response queryTask(@SessionAttribute("user") User user) {
        List<Map<String, String>> list = new ArrayList<>();
        for (Task task : user.getTasks()) {
            Map<String, String> map = new HashMap<>();
            map.put("taskId", task.getId());
            map.put("taskName", task.getName());
            list.add(map);
        }
        return Response.OK(list);
    }

    @RequestMapping(value = "Task/finish")
    @ApiOperation(value = "finishTask")
    @ResponseBody
    public Response finishTask(@SessionAttribute("user") User user) {
        List<Task> tasks = user.getTasks();
        for (Task task : tasks) {
            processEngine.getTaskService().complete(task.getId());
        }
        return Response.OK("clean all");
    }
}
