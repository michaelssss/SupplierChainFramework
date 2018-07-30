package com.jzqh.rzzl2.projectmanagement.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.projectmanagement.impl.ProjectInfo;
import com.jzqh.rzzl2.projectmanagement.repository.ProjectInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
@Controller
@RequestMapping("ProjectInfo")
public class ProjectInfoController {

    private ProjectInfoRepository projectInfoRepository;

    @RequestMapping(value = "add")
    public Response addProjectInfo(ProjectInfo projectInfo) {
        projectInfo.addProjectInfo();
        return Response.OK("");
    }

    @RequestMapping(value = "update")
    public Response updateProjectInfo(ProjectInfo projectInfo) {
        projectInfo.updateProjectInfo();
        return Response.OK("");
    }

    @RequestMapping(value = "delete")
    public Response deleteProjectInfo(Long id) {
        ProjectInfo projectInfo = projectInfoRepository.findOne(id);
        projectInfo.deleteProjectInfo();
        return Response.OK("");
    }

    @RequestMapping(value = "permit")
    public Response projectPermit(Long id) {
        ProjectInfo projectInfo = projectInfoRepository.findOne(id);
        projectInfo.permit();
        return Response.OK("");
    }

    @RequestMapping(value = "queryAll")
    public Response queryAll() {
        List<ProjectInfo> projectInfoList = projectInfoRepository.findAll();
        return Response.OK(projectInfoList);
    }

    @RequestMapping(value = "queryById")
    public Response queryById(Long id) {
        ProjectInfo projectInfo = projectInfoRepository.findOne(id);
        return Response.OK(projectInfo);
    }


}
