package com.michaelssss.rzzl2.basicinfomanagement.controller;


import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.*;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.*;
import com.michaelssss.rzzl2.basicinfomanagement.service.CompanyHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Map;


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
    private CompanyHistoryService companyHistoryService;

    @Autowired
    public CompanyController(CompanyHistoryService companyHistoryService) {
        this.companyHistoryService = companyHistoryService;
    }

    @ResponseBody
    @ApiOperation(value = "添加")
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addCompanyInfo(@RequestBody CompanyImpl company) {
        company.save();
        return Response.OK(company);
    }

    /**
     * 总是查询最新一条
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询")
    @RequestMapping(value = "query", method = RequestMethod.POST)
    public Response queryALL() {
        return Response.OK(companyHistoryService.getAllCompanyLatestHistory());
    }

    @ResponseBody
    @ApiOperation(value = "详情")
    @RequestMapping(value = "detail", method = RequestMethod.POST)
    public Response queryCompanyInfoById(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        return Response.OK(companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId));
    }

    @ResponseBody
    @ApiOperation(value = "审批")
    @RequestMapping(value = "applyAudit", method = RequestMethod.POST)
    public Response applyAudit(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.applyAudit();
        return Response.OK("申请审批成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增联系人")
    @RequestMapping(value = "Contact/add", method = RequestMethod.POST)
    public Response addContact(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        String contactType = map.get("contactType");//联系人类型
        String name = map.get("name");//联系人姓名
        String mobilePhone = map.get("mobilePhone");//移动手机号码
        String phone = map.get("phone");//固话
        String fax = map.get("fax");//传真
        String email = map.get("email");//邮件
        String department = map.get("department");//联系人所属部门
        String position = map.get("position");//职位
        String remark = map.get("remark");//备注
        Contact contact = Contact.builder()
                .contactType(contactType)
                .name(name)
                .mobilePhone(mobilePhone)
                .phone(phone)
                .fax(fax)
                .email(email)
                .department(department)
                .position(position)
                .remark(remark)
                .build();
        Company company = companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.addContacts(contact);
        return Response.OK("新增联系人成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增股东信息")
    @RequestMapping(value = "ShareHolder/add", method = RequestMethod.POST)
    public Response addShareHolder(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        String shareholderName = map.get("shareholderName");
        String fundedOfRatio = map.get("fundedOfRatio");
        String contribution = map.get("contribution");
        String currency = map.get("currency");
        String investmentOfType = map.get("investmentOfType");
        String remark = map.get("remark");
        ShareholderInfo shareholderInfo = ShareholderInfo.builder()
                .shareholderName(shareholderName)
                .fundedOfRatio(fundedOfRatio)
                .contribution(contribution)
                .currency(currency)
                .investmentOfType(investmentOfType)
                .remark(remark).build();
        Company company = companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.addShareHolder(shareholderInfo);
        return Response.OK("新增股东信息成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增账户信息")
    @RequestMapping(value = "BankAccount/add", method = RequestMethod.POST)
    public Response addBankAccount(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        String accountName = map.get("accountName");//账户名称
        String bankAccount = map.get("bankAccount");//银行账号
        String bankName = map.get("bankName");//开户行名称
        String bankFullName = map.get("bankFullName");//开户行全称
        String status = map.get("status");//账号状态
        String currency = map.get("currency");//币种
        String isDefault = map.get("isDefault");//是否为默认账户
        String remark = map.get("remark");//备注
        BankAccount bankAccount1 = BankAccount.builder()
                .accountName(accountName)
                .bankAccount(bankAccount)
                .bankName(bankName)
                .bankFullName(bankFullName)
                .currency(bankAccount)
                .status(status)
                .currency(currency)
                .isDefault(isDefault)
                .remark(remark)
                .build();
        Company company = companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.addBankAccount(bankAccount1);
        return Response.OK("新增账户信息成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增地址信息")
    @RequestMapping(value = "Address/add", method = RequestMethod.POST)
    public Response addAddress(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        String addressType = map.get("addressType");
        String province = map.get("province");
        String city = map.get("city");
        String area = map.get("area");
        String detail = map.get("detail");
        String zipCode = map.get("zipCode");
        String connectPeople = map.get("connectPeople");
        String connectPhone = map.get("connectPhone");
        String connectEmail = map.get("connectEmail");
        String isDefault = map.get("isDefault");
        String remark = map.get("remark");
        Address address = Address.builder()
                .addressType(addressType)
                .province(province)
                .city(city)
                .area(area)
                .detail(detail)
                .zipCode(zipCode)
                .connectPeople(connectPeople)
                .connectPhone(connectPhone)
                .connectEmail(connectEmail)
                .isDefault(isDefault)
                .remark(remark)
                .build();
        Company company = companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.addAddress(address);
        return Response.OK("新增地址成功");
    }

    @ResponseBody
    @ApiOperation(value = "查询所有历史记录")
    @RequestMapping(value = "History/query", method = RequestMethod.POST)
    public Response queryHistory(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        return Response.OK(this.companyHistoryService.getCompanyAllAuditHistory(companyName));
    }

    @ResponseBody
    @ApiOperation(value = "申请成为供货商")
    @RequestMapping(value = "Supplier/apply", method = RequestMethod.POST)
    public Response applySupplier(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        SupplierClientApply supplierClientApply = SupplierClientApply.builder().build();
        Supplier supplier = new SupplierImpl();
        supplier.apply(supplierClientApply, company);
        return Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为采购商")
    @RequestMapping(value = "Purchase/apply", method = RequestMethod.POST)
    public Response applyPurchase(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        PurchaseClientApply purchaseClientApply = PurchaseClientApply.builder().build();
        Purchaser purchaser = PurchaserImpl.builder().build();
        purchaser.apply(purchaseClientApply, company);
        return Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为仓储商")
    @RequestMapping(value = "Storage/apply", method = RequestMethod.POST)
    public Response applyStorage(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        Storage storage = new StorageImpl();
        storage.apply(company);
        return Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为资金方")
    @RequestMapping(value = "Fund/apply", method = RequestMethod.POST)
    public Response applyFund(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        String historyId = map.get("historyId");
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        Funding funding = new FundingImpl();
        funding.apply(company);
        return Response.OK("申请成功");
    }

}
