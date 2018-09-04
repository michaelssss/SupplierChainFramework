package com.michaelssss.rzzl2.contractmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.contractmanagement.impl.SalesContractImpl;
import com.michaelssss.rzzl2.contractmanagement.repository.SalesContractRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/19
 */
@Controller
@RequestMapping("SalesContract")
public class SalesContractController {
    private SalesContractRepository repository;

    @RequestMapping(value = "add")
    public Response addSalesContract(SalesContractImpl salesContract) {
        salesContract.addSalesContract();
        return Response.OK("");
    }

    @RequestMapping(value = "update")
    public Response updateSalesContract(SalesContractImpl salesContract) {
        salesContract.addSalesContract();
        return Response.OK("");
    }

    @RequestMapping(value = "delete")
    public Response deleteSalesContract(Long id) {
        repository.delete(id);
        return Response.OK("");
    }

    @RequestMapping(value = "queryAll")
    public Response queryAll() {
        List<SalesContractImpl> salesContractList = repository.findAll();
        return Response.OK(salesContractList);
    }
}
