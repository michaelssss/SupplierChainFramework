package com.michaelssss.rzzl2.basicinfomanagement.controller;


import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.*;
import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.*;
import com.michaelssss.rzzl2.basicinfomanagement.service.CompanyHistoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


/**
 * Description:公司信息控制层
 *
 * @Author:
 * @Date 2018/7/9
 */
@Api(value = "基础信息", tags = "基础信息")
@Controller
@RequestMapping("Company")
public class CompanyController {
    private CompanyHistoryService companyHistoryService;

    @Autowired
    public CompanyController(CompanyHistoryService companyHistoryService) {
        this.companyHistoryService = companyHistoryService;
    }

    @ResponseBody
    @ApiOperation(value = "添加", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "add", method = RequestMethod.POST, produces = {APPLICATION_JSON_VALUE})
    public Response<CompanyImpl> addCompanyInfo(@RequestBody CompanyImpl company) {
        company.save();
        return (Response<CompanyImpl>) Response.OK(company);
    }

    /**
     * 总是查询最新一条
     *
     * @return
     */
    @ResponseBody
    @ApiOperation(value = "查询", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "list", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<List<Company>> list() {
        return (Response<List<Company>>) Response.OK(companyHistoryService.getAllCompanyLatestHistory());
    }

    @ResponseBody
    @ApiOperation(value = "详情", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "detail", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<List<CompanyImpl>> queryCompanyInfoById(@RequestBody CompanyHistoryDataBinder companyHistoryDataBinder) {
        String companyName = companyHistoryDataBinder.companyName;
        String historyId = companyHistoryDataBinder.historyId;
        return (Response<List<CompanyImpl>>) Response.OK(companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId));
    }

    @ResponseBody
    @ApiOperation(value = "审批", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "applyAudit", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> applyAudit(@RequestBody CompanyHistoryDataBinder companyHistoryDataBinder) {
        String companyName = companyHistoryDataBinder.companyName;
        String historyId = companyHistoryDataBinder.historyId;
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.applyAudit();
        return (Response<String>) Response.OK("申请审批成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增联系人", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Contact/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> addContact(@RequestBody CompanyContractDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        String contactType = map.contactType;//联系人类型
        String name = map.name;//联系人姓名
        String mobilePhone = map.mobilePhone;//移动手机号码
        String phone = map.phone;//固话
        String fax = map.fax;//传真
        String email = map.email;//邮件
        String department = map.department;//联系人所属部门
        String position = map.position;//职位
        String remark = map.remark;//备注
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
        return (Response<String>) Response.OK("新增联系人成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增股东信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "ShareHolder/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> addShareHolder(@RequestBody CompanyShareHolderDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        String shareholderName = map.shareholderName;
        String fundedOfRatio = map.fundedOfRatio;
        String contribution = map.contribution;
        String currency = map.currency;
        String investmentOfType = map.investmentOfType;
        String remark = map.remark;
        ShareholderInfo shareholderInfo = ShareholderInfo.builder()
                .shareholderName(shareholderName)
                .fundedOfRatio(fundedOfRatio)
                .contribution(contribution)
                .currency(currency)
                .investmentOfType(investmentOfType)
                .remark(remark).build();
        Company company = companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        company.addShareHolder(shareholderInfo);
        return (Response<String>) Response.OK("新增股东信息成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增账户信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "BankAccount/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> addBankAccount(@RequestBody CompanyBankAccountDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        String accountName = map.accountName;//账户名称
        String bankAccount = map.bankAccount;//银行账号
        String bankName = map.bankName;//开户行名称
        String bankFullName = map.bankFullName;//开户行全称
        String status = map.status;//账号状态
        String currency = map.currency;//币种
        String isDefault = map.isDefault;//是否为默认账户
        String remark = map.remark;//备注
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
        return (Response<String>) Response.OK("新增账户信息成功");
    }

    @ResponseBody
    @ApiOperation(value = "新增地址信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Address/add", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> addAddress(@RequestBody CompanyAddrDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        String addressType = map.addressType;
        String province = map.province;
        String city = map.city;
        String area = map.area;
        String detail = map.detail;
        String zipCode = map.zipCode;
        String connectPeople = map.connectPeople;
        String connectPhone = map.connectPhone;
        String connectEmail = map.connectEmail;
        String isDefault = map.isDefault;
        String remark = map.remark;
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
        return (Response<String>) Response.OK("新增地址成功");
    }

    @ResponseBody
    @ApiOperation(value = "查询所有历史记录", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "History/query", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<List<CompanyImpl>> queryHistory(@RequestBody Map<String, String> map) {
        String companyName = map.get("companyName");
        return (Response<List<CompanyImpl>>) Response.OK(this.companyHistoryService.getCompanyAllAuditHistory(companyName));
    }

    @ResponseBody
    @ApiOperation(value = "申请成为供货商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Supplier/apply", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> applySupplier(@RequestBody CompanyHistoryDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        SupplierClientApply supplierClientApply = SupplierClientApply.builder().build();
        Supplier supplier = new SupplierImpl();
        supplier.apply(supplierClientApply, company);
        return (Response<String>) Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为采购商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Purchase/apply", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> applyPurchase(@RequestBody CompanyHistoryDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        PurchaseClientApply purchaseClientApply = PurchaseClientApply.builder().build();
        Purchaser purchaser = PurchaserImpl.builder().build();
        purchaser.apply(purchaseClientApply, company);
        return (Response<String>) Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为仓储商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Storage/apply", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> applyStorage(@RequestBody CompanyHistoryDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        Storage storage = new StorageImpl();
        storage.apply(company);
        return (Response<String>) Response.OK("申请成功");
    }

    @ResponseBody
    @ApiOperation(value = "申请成为资金方", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
    @RequestMapping(value = "Fund/apply", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
    public Response<String> applyFund(@RequestBody CompanyHistoryDataBinder map) {
        String companyName = map.companyName;
        String historyId = map.historyId;
        Company company = this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(companyName, historyId);
        Funding funding = new FundingImpl();
        funding.apply(company);
        return (Response<String>) Response.OK("申请成功");
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class CompanyHistoryDataBinder {
        private String companyName;
        private String historyId;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class CompanyAddrDataBinder {
        private String companyName;
        private String historyId;
        @ApiModelProperty(value = "地址类型", example = "国内")
        private String addressType;
        @ApiModelProperty(value = "省份", example = "广东")
        private String province;
        @ApiModelProperty(value = "城市", example = "深圳")
        private String city;
        @ApiModelProperty(value = "区域", example = "南山区")
        private String area;
        @ApiModelProperty(value = "详情", example = "广东深圳南山区哈哈哈哈哈")
        private String detail;
        @ApiModelProperty(value = "邮政编码", example = "543002")
        private String zipCode;//邮政编码
        @ApiModelProperty(value = "联系人", example = "Michaelssss")
        private String connectPeople;
        @ApiModelProperty(value = "联系人手机", example = "13800000000")
        private String connectPhone;
        @ApiModelProperty(value = "联系人Email", example = "test@Test.com")
        private String connectEmail;
        @ApiModelProperty(value = "是否默认联系人", allowableValues = "true,false")
        private String isDefault;
        @ApiModelProperty(value = "备注")
        private String remark;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class CompanyShareHolderDataBinder {
        private String companyName;
        private String historyId;
        @ApiModelProperty(value = "股东姓名", example = "Michaelssss")
        private String shareholderName;
        @ApiModelProperty(value = "持股比例", example = "100%")
        private String fundedOfRatio;
        @ApiModelProperty(value = "")
        private String contribution;
        @ApiModelProperty(value = "币种", example = "人民币")
        private String currency;
        @ApiModelProperty(value = "投资类型", example = "股权")
        private String investmentOfType;
        @ApiModelProperty(value = "备注", example = "")
        private String remark;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class CompanyContractDataBinder {
        private String companyName;
        private String historyId;
        @ApiModelProperty(value = "联系人类型", example = "唯一联系人")
        private String contactType;
        @ApiModelProperty(value = "联系人姓名", example = "Michaelssss")
        private String name;
        @ApiModelProperty(value = "手机号码", example = "13800000000")
        private String mobilePhone;
        @ApiModelProperty(value = "固话", example = "028-12345678")
        private String phone;
        @ApiModelProperty(value = "传真", example = "")
        private String fax;
        @ApiModelProperty(value = "邮件", example = "test@test.com")
        private String email;
        @ApiModelProperty(value = "联系人所属部门", example = "挖矿部")
        private String department;
        @ApiModelProperty(value = "职位", example = "CEO")
        private String position;
        @ApiModelProperty(value = "备注", example = "")
        private String remark;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    private static class CompanyBankAccountDataBinder {
        private String companyName;
        private String historyId;
        @ApiModelProperty(value = "账户名称", example = "收款")
        private String accountName;

        @ApiModelProperty(value = "银行账号", example = "1234567890")
        private String bankAccount;

        @ApiModelProperty(value = "开户行名称", example = "中国建设银行")
        private String bankName;

        @ApiModelProperty(value = "开户行全称", example = "中国健身银行深圳市南山支行")
        private String bankFullName;

        @ApiModelProperty(value = "账号状态", example = "可用")
        private String status;

        @ApiModelProperty(value = "币种", example = "人民币")
        private String currency;

        @ApiModelProperty(value = "是否为默认账户", example = "true")
        private String isDefault;

        @ApiModelProperty(value = "备注")
        private String remark;
    }
}
