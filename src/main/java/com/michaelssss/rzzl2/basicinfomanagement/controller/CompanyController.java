package com.michaelssss.rzzl2.basicinfomanagement.controller;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.respository.CompanyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;


/**
 * Description:公司信息控制层
 *
 * @Author:
 * @Date 2018/7/9
 */
@Api(value = "公司信息")
@Controller
@RequestMapping("Company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;


    @ResponseBody
    @ApiOperation(value = "添加")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addCompanyInfo(@RequestBody CompanyImpl company) {
        company.addInfo();
        return Response.OK(company);
    }

    @ResponseBody
    @ApiOperation(value = "查询")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public Response queryALL() {
        List<CompanyImpl> hibernateDelegateList = SpringContextHolder.getBean(CompanyRepository.class).findAll();
        return Response.OK(hibernateDelegateList);
    }

    @ResponseBody
    @ApiOperation(value = "详情")
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Response queryCompanyInfoById() {
        return Response.OK(null);
    }

    @ResponseBody
    @ApiOperation(value = "审批")
    @RequestMapping(value = "applyAudit", method = RequestMethod.POST)
    public Response applyAudit() {
        return Response.OK(null);
    }

    @ResponseBody
    @ApiOperation(value = "新增联系人")
    @RequestMapping(value = "Contact/add", method = RequestMethod.POST)
    public Response addContact() {
        return Response.OK(null);
    }

    @ResponseBody
    @ApiOperation(value = "新增股东信息")
    @RequestMapping(value = "ShareHolder/add", method = RequestMethod.POST)
    public Response addShareHolder() {
        return Response.OK(null);
    }

    @ResponseBody
    @ApiOperation(value = "新增账户信息")
    @RequestMapping(value = "BankAccount/add", method = RequestMethod.POST)
    public Response addBankAccount() {
        return Response.OK(null);
    }

    @ResponseBody
    @ApiOperation(value = "新增地址信息")
    @RequestMapping(value = "Address/add", method = RequestMethod.POST)
    public Response addAddress() {
        return Response.OK(null);
    }

}
