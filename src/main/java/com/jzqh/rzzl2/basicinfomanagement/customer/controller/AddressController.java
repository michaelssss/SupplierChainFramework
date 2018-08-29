package com.jzqh.rzzl2.basicinfomanagement.customer.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.Address;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
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
@Api(value = "公司controller")
@Controller
@RequestMapping(name="Address")
public class AddressController {
    @Autowired
    private CompanyRepository repository;
    @ApiOperation(value = "添加地址", tags = "添加地址接口")
    @ResponseBody
    @RequestMapping(value = "add")
    public Response addAddress(@ApiParam(name = "id", value = "地址id", required = true) @RequestParam(value = "id") Long id, @RequestBody Address address) {
        CompanyImpl company = repository.findOne(id);
        company.addAddress(address);
        company.updateInfo();
        return Response.OK("");
    }

    @ApiOperation(value = "修改地址", tags = "修改地址接口")
    @ResponseBody
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public Response updateAddress(@ApiParam(name = "address", value = "地址") @RequestBody Address address) {
        address.updateInfo();
        return Response.OK(address);
    }

    @ResponseBody
    @ApiOperation(value = "删除地址", tags = "删除地址接口")
    @RequestMapping("delete")
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
