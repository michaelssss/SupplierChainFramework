package com.jzqh.rzzl2.basicinfomanagement.customer.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.BankAccount;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/8/29
 */
@Controller
@RequestMapping("BankAccount")
public class BankAccountController {
    @Autowired
    private CompanyRepository repository;
    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addBankAccount(@RequestParam(value = "id") Long id, @RequestBody BankAccount bankAccount) {
        CompanyImpl company = repository.findOne(id);
        company.addBankAccount(bankAccount);
        company.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response updateBankAccount(BankAccount bankAccount) {
        bankAccount.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "delete", method = RequestMethod.POST)
    public Response deleteBankAccount(@RequestParam(value = "id") Long id, @RequestBody BankAccount bankAccount) {
        CompanyImpl company = repository.findOne(id);
        company.deleteBankAccount(bankAccount);
        company.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public Response queryBankAccountInfo(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getBankAccounts());
    }
}
