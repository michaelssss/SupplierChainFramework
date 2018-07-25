package com.jzqh.rzzl2.basicinfomanagement.customer.controller;


import com.jzqh.SpringContextHolder;
import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.*;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
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
@RequestMapping(path = "Company")
public class CompanyController {
    @Autowired
    private CompanyRepository repository;

    @ApiOperation(value = "添加地址", tags = "添加地址接口")
    @ResponseBody
    @RequestMapping(value = "Address/add")
    public Response addAddress(@ApiParam(name = "id", value = "地址id", required = true) @RequestParam(value = "id") Long id, @RequestBody Address address) {
        CompanyImpl company = repository.findOne(id);
        company.addAddress(address);
        company.updateInfo();
        return Response.OK(null);
    }

    @ApiOperation(value = "修改地址", tags = "修改地址接口")
    @ResponseBody
    @RequestMapping(value = "Address/update", method = RequestMethod.POST)
    public Response updateAddress(@ApiParam(name = "address", value = "地址") @RequestBody Address address) {
        address.updateInfo();
        return Response.OK(address);
    }

    @ResponseBody
    @ApiOperation(value = "删除地址", tags = "删除地址接口")
    @RequestMapping("Address/delete")
    public Response deleteAddress(@RequestParam(value = "id") Long id, @RequestBody Address address) {
        CompanyImpl company = repository.findOne(id);
        company.deleteAddress(address);
        company.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "Address/queryAll", method = RequestMethod.GET)
    public Response queryALL(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getAddressSet());
    }

    @ResponseBody
    @RequestMapping(value = "BankAccount/add", method = RequestMethod.POST)
    public Response addBankAccount(@RequestParam(value = "id") Long id, @RequestBody BankAccount bankAccount) {
        CompanyImpl company = repository.findOne(id);
        company.addBankAccount(bankAccount);
        company.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "BankAccount/update", method = RequestMethod.POST)
    public Response updateBankAccount(BankAccount bankAccount) {
        bankAccount.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "BankAccount/delete", method = RequestMethod.POST)
    public Response deleteBankAccount(@RequestParam(value = "id") Long id, @RequestBody BankAccount bankAccount) {
        CompanyImpl company = repository.findOne(id);
        company.deleteBankAccount(bankAccount);
        company.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "BankAccount/queryAll", method = RequestMethod.GET)
    public Response queryBankAccountInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getBankAccounts());
    }

    @ResponseBody
    @RequestMapping(value = "ShareHolder/add", method = RequestMethod.POST)
    public Response addShareHolderInfo(@RequestParam(value = "id") Long id, @RequestBody ShareholderInfo shareholderInfo) {
        CompanyImpl company = repository.findOne(id);
        company.addShareHolder(shareholderInfo);
        company.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("ShareHolder/update")
    public Response updateShareHolderInfo(@RequestBody ShareholderInfo shareholderInfo) {
        shareholderInfo.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("ShareHolder/delete")
    public Response deleteShareHolderInfoById(@RequestBody ShareholderInfo shareholderInfo) {
        shareholderInfo.deleteInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("ShareHolder/queryAll")
    public Response queryShareHolderInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getShareholderInfoSet());
    }

    @ResponseBody
    @RequestMapping(value = "Contacts/add", method = RequestMethod.POST)
    public Response addContactInfo(@RequestParam(value = "id") Long id, @RequestBody Contact contact) {
        CompanyImpl company = repository.findOne(id);
        company.addContacts(contact);
        company.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("Contacts/update")
    public Response updateContactInfo(@RequestBody Contact contact) {
        contact.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("Contacts/delete")
    public Response deleteContactInfoById(@RequestBody Contact contact) {
        contact.deleteInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("Contacts/queryAll")
    public Response queryContactInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getContactSet());
    }

    @ResponseBody
    @RequestMapping(path = "add", method = RequestMethod.POST)
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
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "queryALL", method = RequestMethod.GET)
    public Response queryALL() {
        List<CompanyImpl> hibernateDelegateList = SpringContextHolder.getBean(CompanyRepository.class).findAll();
        return Response.OK(hibernateDelegateList);
    }

    @ResponseBody
    @RequestMapping(value = "query/id", method = RequestMethod.GET)
    public Response queryCompanyInfoById(@RequestParam(value = "id") Long id) {
        CompanyImpl company = repository.findOne(id);
        return Response.OK(company);
    }

}
