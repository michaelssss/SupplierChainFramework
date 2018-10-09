package com.michaelssss.business.contractmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.business.contractmanagement.Contract;
import com.michaelssss.business.contractmanagement.impl.FrameContractImpl;
import com.michaelssss.business.contractmanagement.impl.PurchaseContractImpl;
import com.michaelssss.business.contractmanagement.impl.SalesContractImpl;
import com.michaelssss.business.contractmanagement.repository.FrameContractRepository;
import com.michaelssss.business.contractmanagement.repository.PurchaseContactRepository;
import com.michaelssss.business.contractmanagement.repository.SalesContractRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import java.util.Map;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "合同管理", tags = "合同管理")
@RequestMapping(value = "Contract")
public class ContractController {

  private FrameContractRepository frameContractRepository;
  private SalesContractRepository salesContractRepository;
  private PurchaseContactRepository purchaseContactRepository;

  @RequestMapping(value = "Framework/add", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "添加框架合同", tags = "合同管理")
  public Response addFrameworkContract(@RequestBody FrameContractImpl contract) {
    contract.add();
    return Response.OK("添加框架合同成功");
  }

  @RequestMapping(value = "Framework/update", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "更新框架合同", tags = "合同管理")
  public Response updateFrameworkContract(@RequestBody FrameContractImpl contract) {
    FrameContractImpl contract1 = frameContractRepository.findById(contract.getId()).get();
    BeanUtils.copyProperties(contract, contract1, "id", "audit_state");
    contract1.update();
    return Response.OK("更新框架合同成功");
  }

  @RequestMapping(value = "Framework/confirm", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "确认框架合同", tags = "合同管理")
  public Response confirmFrameworkContract(@RequestBody Map<String, Long> map) {
    Contract contract = frameContractRepository.findById(map.get("id")).get();
    contract.confirm();
    return Response.OK("确认框架合同成功");
  }

  @RequestMapping(value = "Sales/add", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "添加销售合同", tags = "合同管理")
  public Response addSalesContract(@RequestBody SalesContractImpl contract) {
    contract.add();
    return Response.OK("添加销售合同成功");
  }

  @RequestMapping(value = "Sales/update", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "更新销售合同", tags = "合同管理")
  public Response updateSalesContract(@RequestBody SalesContractImpl contract) {
    SalesContractImpl contract1 = salesContractRepository.findById(contract.getId()).get();
    BeanUtils.copyProperties(contract, contract1, "id", "audit_state");
    contract1.update();
    return Response.OK("更新销售合同成功");
  }

  @RequestMapping(value = "Sales/confirm", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "确认销售合同", tags = "合同管理")
  public Response confirmSalesContract(@RequestBody Map<String, Long> map) {
    Contract contract = salesContractRepository.findById(map.get("id")).get();
    contract.confirm();
    return Response.OK("确认销售合同成功");
  }

  @RequestMapping(value = "Purchase/add", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "添加采购合同", tags = "合同管理")
  public Response addPurchaseContract(@RequestBody PurchaseContractImpl contract) {
    contract.add();
    return Response.OK("添加采购合同成功");
  }

  @RequestMapping(value = "Purchase/update", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "更新采购合同", tags = "合同管理")
  public Response updatePurchaseContract(@RequestBody PurchaseContractImpl contract) {
    PurchaseContractImpl contract1 = purchaseContactRepository.findById(contract.getId()).get();
    BeanUtils.copyProperties(contract, contract1, "id", "audit_state");
    contract1.update();
    return Response.OK("更新采购合同成功");
  }

  @RequestMapping(value = "Purchase/confirm", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "确认采购合同", tags = "合同管理")
  public Response confirmPurchaseContract(@RequestBody Map<String, Long> map) {
    Contract contract = purchaseContactRepository.findById(map.get("id")).get();
    contract.confirm();
    return Response.OK("确认采购合同成功");
  }

  @RequestMapping(value = "Purchase/list", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "列出采购合同", tags = "合同管理")
  public Response listPurchaseContract() {
    return Response.OK(purchaseContactRepository.findAll());
  }

  @RequestMapping(value = "Sales/list", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "列出销售合同", tags = "合同管理")
  public Response listSalesContract() {
    return Response.OK(salesContractRepository.findAll());
  }

  @RequestMapping(value = "Framework/list", method = RequestMethod.POST)
  @ResponseBody
  @ApiOperation(value = "列出框架合同", tags = "合同管理")
  public Response listFrameworkContract() {
    return Response.OK(frameContractRepository.findAll());
  }
}
