package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


/**
 * Description:公司信息控制层
 *
 * @Author:
 * @Date 2018/7/9
 */
@Api(value = "公司controller")
@Controller
@RequestMapping("Company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;
    @ResponseBody
    @RequestMapping(value = "CompanyInfo/Test", method = RequestMethod.GET)
    public Response testCompanyInfo() {
        return Response.OK("");
    }
    @ResponseBody
    @RequestMapping(value = "CompanyInfo/add", method = RequestMethod.POST)
    public Response addCompanyInfo(@RequestBody CompanyImpl company) {
        company.addInfo();
        return Response.OK(company);
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response updateCompanyInfo(@RequestBody CompanyImpl company) {
        company.updateInfo();
        return Response.OK(company);
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response deleteCompanyInfo(@RequestParam(value = "id") Long id) {
        repository.delete(id);
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "queryALL", method = RequestMethod.GET)
    public Response queryALL() {
        List<CompanyImpl> hibernateDelegateList = SpringContextHolder.getBean(CompanyRepository.class).findAll();
        return Response.OK(hibernateDelegateList);
    }

    @ResponseBody
    @RequestMapping(value = "id", method = RequestMethod.GET)
    public Response queryCompanyInfoById(@RequestParam(value = "id") Long id) {
        CompanyImpl company = repository.findOne(id);
        return Response.OK(company);
    }

}
