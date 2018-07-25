package com.jzqh.rzzl2.bpm;

import com.jzqh.rzzl2.SpringBootTestBasic;
import org.activiti.engine.ProcessEngine;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class BpmHelloWorld extends SpringBootTestBasic {
    private static Logger logger = LoggerFactory.getLogger(BpmHelloWorld.class);
    @Autowired
    private ProcessEngine engine;

    @Test
    public void HelloWorldTest() {
        ProcessInstance instance = engine.getRuntimeService().startProcessInstanceByKey("myProcess");
        String processId = instance.getId();
        List<Task> taskList = engine.getTaskService().createTaskQuery().taskCandidateUser("management").list();
        for (Task task : taskList) {
            logger.debug(task.toString());
            engine.getTaskService().complete(task.getId());
        }
        logger.debug(engine.getHistoryService().createHistoricProcessInstanceQuery().processInstanceId(processId).list().toString());
    }
}
