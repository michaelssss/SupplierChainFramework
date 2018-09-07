package com.michaelssss.rzzl2.projectmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.projectmanagement.Approval;
import com.michaelssss.rzzl2.projectmanagement.impl.ApprovalImpl;
import com.michaelssss.rzzl2.projectmanagement.impl.ProjectImpl;
import com.michaelssss.rzzl2.projectmanagement.repository.ApprovalCatalog;
import com.michaelssss.rzzl2.projectmanagement.repository.ProjectCatalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(value = "ProjectManagement")
@Api(value = "项目管理")
public class ProjectController {
    private ProjectCatalog projectCatalog;
    private ApprovalCatalog approvalCatalog;

    @Autowired
    public ProjectController(ProjectCatalog projectCatalog, ApprovalCatalog approvalCatalog) {
        this.projectCatalog = projectCatalog;
        this.approvalCatalog = approvalCatalog;
    }

    @RequestMapping(value = "Project/list", method = RequestMethod.POST)
    @ApiOperation(value = "所有项目")
    @ResponseBody
    public Response listProject() {
        return Response.OK(projectCatalog.findAll());
    }

    @RequestMapping(value = "Project/detail", method = RequestMethod.POST)
    @ApiOperation(value = "项目详情")
    @ResponseBody
    public Response detailProject(@RequestBody ProjectImpl project) {
        return Response.OK(projectCatalog.findOne(project.getId()));
    }

    @RequestMapping(value = "Project/Approval/list", method = RequestMethod.POST)
    @ApiOperation(value = "所有批复")
    @ResponseBody
    public Response listProjectApproval() {
        return Response.OK(approvalCatalog.findAll());
    }

    @RequestMapping(value = "Project/Approval/detail", method = RequestMethod.POST)
    @ApiOperation(value = "批复详情")
    @ResponseBody
    public Response detailProjectApproval(@RequestBody ApprovalImpl approval) {
        return Response.OK(approvalCatalog.findOne(approval.getId()));
    }

    @RequestMapping(value = "Project/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加项目")
    @ResponseBody
    public Response addProject(@RequestBody ProjectImpl project) {
        project.addProject();
        return Response.OK("添加项目成功");
    }

    @RequestMapping(value = "Project/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新项目")
    @ResponseBody
    public Response updateProject(@RequestBody ProjectImpl project) {
        ProjectImpl instance = projectCatalog.findOne(project.getId());
        BeanUtils.copyProperties(project, instance, "id", "state");
        instance.updateProject();
        return Response.OK("更新项目成功");
    }

    @RequestMapping(value = "Project/apply", method = RequestMethod.POST)
    @ApiOperation(value = "申请项目")
    @ResponseBody
    public Response applyProject(@RequestBody ProjectImpl project) {
        ProjectImpl instance = projectCatalog.findOne(project.getId());
        instance.apply();
        return Response.OK("项目申请成功");
    }

    @RequestMapping(value = "Project/Approval/add", method = RequestMethod.POST)
    @ApiOperation(value = "添加项目批复")
    @ResponseBody
    public Response addProjectApproval(@RequestBody ApprovalImpl approval) {
        approval.addApproval();
        return Response.OK("添加项目批复成功");
    }

    @RequestMapping(value = "Project/Approval/update", method = RequestMethod.POST)
    @ApiOperation(value = "更新项目批复")
    @ResponseBody
    public Response updateProjectApproval(@RequestBody ApprovalImpl approval) {
        Approval instance = approvalCatalog.findOne(approval.getId());
        BeanUtils.copyProperties(approval, instance, "id", "state");
        instance.updateApproval();
        return Response.OK("添加项目批复成功");
    }

    @RequestMapping(value = "Project/Approval/confirm", method = RequestMethod.POST)
    @ApiOperation(value = "确认项目批复")
    @ResponseBody
    public Response confirmProjectApproval(@RequestBody ApprovalImpl approval) {
        Approval instance = approvalCatalog.findOne(approval.getId());
        instance.confirm();
        return Response.OK("确认项目批复成功");
    }
}
