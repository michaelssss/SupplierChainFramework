package com.michaelssss.business.basicinfomanagement.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import com.michaelssss.account.User;
import com.michaelssss.base.Response;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Funding;
import com.michaelssss.business.basicinfomanagement.Purchaser;
import com.michaelssss.business.basicinfomanagement.Storage;
import com.michaelssss.business.basicinfomanagement.Supplier;
import com.michaelssss.business.basicinfomanagement.domain.Address;
import com.michaelssss.business.basicinfomanagement.domain.BankAccount;
import com.michaelssss.business.basicinfomanagement.domain.CompanyImpl;
import com.michaelssss.business.basicinfomanagement.domain.Contact;
import com.michaelssss.business.basicinfomanagement.domain.FundingImpl;
import com.michaelssss.business.basicinfomanagement.domain.PurchaserImpl;
import com.michaelssss.business.basicinfomanagement.domain.ShareholderInfo;
import com.michaelssss.business.basicinfomanagement.domain.StorageImpl;
import com.michaelssss.business.basicinfomanagement.domain.SupplierImpl;
import com.michaelssss.business.basicinfomanagement.service.CompanyHistoryService;
import com.michaelssss.business.basicinfomanagement.service.CompanyService;
import com.michaelssss.utils.PageUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttribute;

/**
 * Description:公司信息控制层
 *
 * @author Michaelssss
 * @date 2018/7/9
 */
@Api(value = "基础信息", tags = "基础信息")
@Controller
@RequestMapping("Company")
public class CompanyController {

  private CompanyHistoryService companyHistoryService;
  private CompanyService companyService;

  @Autowired
  public CompanyController(
      CompanyHistoryService companyHistoryService, CompanyService companyService) {
    this.companyHistoryService = companyHistoryService;
    this.companyService = companyService;
  }

  @ResponseBody
  @ApiOperation(value = "添加", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "add",
      method = RequestMethod.POST,
      produces = {APPLICATION_JSON_VALUE})
  public Response<CompanyImpl> addCompanyInfo(@RequestBody CompanyImpl company) {
    company.save();
    return (Response<CompanyImpl>) Response.OK(company);
  }

  /** 总是查询最新一条 */
  @ResponseBody
  @ApiOperation(value = "查询", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(value = "list", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
  public Response<List<Company>> list(
      @RequestBody CompanySearchConditionBinder companySearchConditionBinder,
      HttpServletRequest request,
      HttpServletResponse response) {
    CompanyImpl sample =
        CompanyImpl.builder()
            .companyName(
                StringUtils.isEmpty(companySearchConditionBinder.getCompanyName())
                    ? null
                    : companySearchConditionBinder.getCompanyName())
            .companyType(
                StringUtils.isEmpty(companySearchConditionBinder.getCompanyType())
                    ? null
                    : companySearchConditionBinder.getCompanyType())
            .build();
    List<Company> companies = companyHistoryService.getAllCompanyLatestHistory(sample);
    Pageable pageable = PageUtils.getPageableFromRequest(request);
    Page<Company> companyPage = (Page<Company>) PageUtils.getPageFromPageable(companies, pageable);
    PageUtils.writeResponsePageHeader(companyPage, response);
    return (Response<List<Company>>) Response.OK(companyPage.getContent());
  }

  @ResponseBody
  @ApiOperation(value = "详情", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(value = "detail", method = RequestMethod.POST, produces = APPLICATION_JSON_VALUE)
  public Response<CompanyImpl> queryCompanyInfoById(
      @RequestBody CompanyHistoryDataBinder companyHistoryDataBinder) {
    String companyName = companyHistoryDataBinder.getCompanyName();
    String historyId = companyHistoryDataBinder.getHistoryId();
    return Response.OK(
        (CompanyImpl)
            companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
                companyName, historyId));
  }

  @ResponseBody
  @ApiOperation(value = "审批", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "applyAudit",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  public Response<String> applyAudit(
      @RequestBody CompanyHistoryDataBinder companyHistoryDataBinder) {
    String companyName = companyHistoryDataBinder.getCompanyName();
    String historyId = companyHistoryDataBinder.getHistoryId();
    companyService.startApplyAudit(companyName, historyId);
    return (Response<String>) Response.OK("申请审批成功");
  }

  @ResponseBody
  @ApiOperation(value = "新增联系人", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Contact/add",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  public Response<String> addContact(@RequestBody CompanyContactDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    String contactType = map.getContactType(); // 联系人类型
    String name = map.getName(); // 联系人姓名
    String mobilePhone = map.getMobilePhone(); // 移动手机号码
    String phone = map.getPhone(); // 固话
    String fax = map.getFax(); // 传真
    String email = map.getEmail(); // 邮件
    String department = map.getDepartment(); // 联系人所属部门
    String position = map.getPosition(); // 职位
    String remark = map.getRemark(); // 备注
    String isDefault = map.getIsDefault();
    Contact contact =
        Contact.builder()
            .contactType(contactType)
            .name(name)
            .mobilePhone(mobilePhone)
            .phone(phone)
            .fax(fax)
            .isDefault(isDefault)
            .email(email)
            .department(department)
            .position(position)
            .remark(remark)
            .build();
    Company company =
        companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    company.addContacts(contact);
    return (Response<String>) Response.OK("新增联系人成功");
  }

  @ResponseBody
  @ApiOperation(value = "新增股东信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "ShareHolder/add",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> addShareHolder(@RequestBody CompanyShareHolderDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    String shareholderName = map.getShareholderName();
    String fundedOfRatio = map.getFundedOfRatio();
    String contribution = map.getContribution();
    String currency = map.getCurrency();
    String investmentOfType = map.getInvestmentOfType();
    String remark = map.getRemark();
    ShareholderInfo shareholderInfo =
        ShareholderInfo.builder()
            .shareholderName(shareholderName)
            .fundedOfRatio(fundedOfRatio)
            .contribution(contribution)
            .currency(currency)
            .investmentOfType(investmentOfType)
            .remark(remark)
            .build();
    Company company =
        companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    company.addShareHolder(shareholderInfo);
    return (Response<String>) Response.OK("新增股东信息成功");
  }

  @ResponseBody
  @ApiOperation(value = "新增账户信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "BankAccount/add",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> addBankAccount(@RequestBody CompanyBankAccountDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    String accountName = map.getAccountName(); // 账户名称
    String bankAccount = map.getBankAccount(); // 银行账号
    String bankName = map.getBankName(); // 开户行名称
    String bankFullName = map.getBankFullName(); // 开户行全称
    String status = map.getStatus(); // 账号状态
    String currency = map.getCurrency(); // 币种
    String isDefault = map.getIsDefault(); // 是否为默认账户
    String remark = map.getRemark(); // 备注
    BankAccount bankAccount1 =
        BankAccount.builder()
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
    Company company =
        companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    company.addBankAccount(bankAccount1);
    return (Response<String>) Response.OK("新增账户信息成功");
  }

  @ResponseBody
  @ApiOperation(value = "新增地址信息", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Address/add",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> addAddress(@RequestBody CompanyAddrDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    String addressType = map.getAddressType();
    String province = map.getProvince();
    String city = map.getCity();
    String area = map.getArea();
    String detail = map.getDetail();
    String zipCode = map.getZipCode();
    String remark = map.getRemark();
    String isDefault = map.getIsDefault();
    Address address =
        Address.builder()
            .addressType(addressType)
            .province(province)
            .isDefault(isDefault)
            .city(city)
            .area(area)
            .detail(detail)
            .zipCode(zipCode)
            .remark(remark)
            .build();
    Company company =
        companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    company.addAddress(address);
    return (Response<String>) Response.OK("新增地址成功");
  }

  @ResponseBody
  @ApiOperation(value = "查询所有历史记录", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "History/query",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  public Response<List<Company>> queryHistory(@RequestBody Map<String, String> map) {
    String companyName = map.get("companyName");
    return Response.OK(this.companyHistoryService.getCompanyAllAuditHistory(companyName));
  }

  @ResponseBody
  @ApiOperation(value = "申请成为供货商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Supplier/apply",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> applySupplier(
      @SessionAttribute("user") User user, @RequestBody CompanyHistoryDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    Company company =
        this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    Supplier supplier = new SupplierImpl();
    supplier.apply(user, company);
    return (Response<String>) Response.OK("申请成功");
  }

  @ResponseBody
  @ApiOperation(value = "申请成为采购商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Purchase/apply",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> applyPurchase(
      @SessionAttribute("user") User user, @RequestBody CompanyHistoryDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    Company company =
        this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    Purchaser purchaser = PurchaserImpl.builder().build();
    purchaser.apply(user, company);
    return (Response<String>) Response.OK("申请成功");
  }

  @ResponseBody
  @ApiOperation(value = "申请成为仓储商", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Storage/apply",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> applyStorage(
      @SessionAttribute("user") User user, @RequestBody CompanyHistoryDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    Company company =
        this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    Storage storage = new StorageImpl();
    storage.apply(user, company);
    return (Response<String>) Response.OK("申请成功");
  }

  @ResponseBody
  @ApiOperation(value = "申请成为资金方", tags = "基础信息", produces = APPLICATION_JSON_VALUE)
  @RequestMapping(
      value = "Fund/apply",
      method = RequestMethod.POST,
      produces = APPLICATION_JSON_VALUE)
  @Transactional
  public Response<String> applyFund(
      @SessionAttribute("user") User user, @RequestBody CompanyHistoryDataBinder map) {
    String companyName = map.getCompanyName();
    String historyId = map.getHistoryId();
    Company company =
        this.companyHistoryService.getSpecialCompanyHistoryByHistoryIdAndCompanyName(
            companyName, historyId);
    Funding funding = new FundingImpl();
    funding.apply(user, company);
    return (Response<String>) Response.OK("申请成功");
  }
}
