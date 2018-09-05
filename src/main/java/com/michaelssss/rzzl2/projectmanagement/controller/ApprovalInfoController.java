package com.michaelssss.rzzl2.projectmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.projectmanagement.impl.ApprovalInfo;
import com.michaelssss.rzzl2.projectmanagement.repository.ApproalInfoRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
@Controller
@RequestMapping("ApprovalInfo")
public class ApprovalInfoController {

    private ApproalInfoRepository approalInfoRepository;

    @RequestMapping("add")
    public Response addApprovalInfo(ApprovalInfo approvalInfo) {
        approvalInfo.addProjectApprovalInfo();
        return Response.OK("");
    }

    @RequestMapping("update")
    public Response updateApprovalInfo(ApprovalInfo approvalInfo) {
        approvalInfo.updateProjectInfo();
        return Response.OK("");
    }

    @RequestMapping("delete")
    public Response deleteApprovalInfo(Long id) {
        ApprovalInfo approvalInfo = approalInfoRepository.findOne(id);
        approvalInfo.deleteProjectInfo();
        return Response.OK("");
    }

    public Response takeEffect(Long id) {
        ApprovalInfo approvalInfo = approalInfoRepository.findOne(id);
        approvalInfo.confirm();
        return Response.OK("");
    }

}
