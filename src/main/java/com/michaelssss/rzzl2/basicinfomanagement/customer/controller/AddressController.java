package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.Address;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 公司地址信息
 * @Author:tanshaoxing
 * @Date:2018/8/29
 */

@Controller
@RequestMapping(value = "Address")
@Api(value = "公司地址信息管理", tags = "/Address", description = "公司地址信息管理")
public class AddressController {
    @Autowired
    private CompanyRepository repository;

    @ResponseBody
    @RequestMapping(value = "add")
    @ApiOperation(value = "添加地址", httpMethod = "POST")
    public Response addAddress(@ApiParam(name = "id", value = "地址id", required = true) @RequestParam(value = "id") Long id, @RequestBody Address address) {
        CompanyImpl company = repository.findOne(id);
        company.addAddress(address);
        company.updateInfo();
        return Response.OK("");
    }


    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    @ApiOperation(value = "修改地址")
    public Response updateAddress(@ApiParam(name = "address", value = "地址") @RequestBody Address address) {
        address.updateInfo();
        return Response.OK(address);
    }

    @ResponseBody
    @RequestMapping("delete")
    @ApiOperation(value = "删除地址")
    public Response deleteAddress(@RequestParam(value = "id") Long id, @RequestBody Address address) {
        CompanyImpl company = repository.findOne(id);
        company.deleteAddress(address);
        company.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "queryAll", method = RequestMethod.GET)
    public Response queryALL(@RequestParam(value = "companyId") Long companyId) {
        CompanyImpl company = repository.findOne(companyId);
        return Response.OK(company.getAddressSet());
    }
}
