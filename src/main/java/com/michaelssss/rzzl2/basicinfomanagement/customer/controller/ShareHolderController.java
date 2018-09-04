package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.ShareholderInfo;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/8/29
 */
@Controller
@RequestMapping("ShareHolder")
public class ShareHolderController {
    @Autowired
    private CompanyRepository repository;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addShareHolderInfo(@RequestParam(value = "id") Long id, @RequestBody ShareholderInfo shareholderInfo) {
        CompanyImpl company = repository.findOne(id);
        company.addShareHolder(shareholderInfo);
        company.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateShareHolderInfo(@RequestBody ShareholderInfo shareholderInfo) {
        shareholderInfo.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("delete")
    public Response deleteShareHolderInfoById(@RequestBody ShareholderInfo shareholderInfo) {
        shareholderInfo.deleteInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryShareHolderInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getShareholderInfoSet());
    }
}
