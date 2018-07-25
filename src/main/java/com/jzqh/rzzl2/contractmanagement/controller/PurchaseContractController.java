package com.jzqh.rzzl2.contractmanagement.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.contractmanagement.impl.PurchaseContractImpl;
import com.jzqh.rzzl2.contractmanagement.repository.PurchaseContactRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/19
 */
@Controller
@RequestMapping(path = "PurchaseContract")
public class PurchaseContractController {

    private PurchaseContactRepository repository;

    @RequestMapping(value = "add")
    public Response addPurchaseContract(PurchaseContractImpl purchaseContract) {
        purchaseContract.addPurchaseContract();
        return Response.OK("");
    }

    @RequestMapping(value = "update")
    public Response updatePurchaseContract(PurchaseContractImpl purchaseContract) {
        purchaseContract.updatePurchaseContract();
        return Response.OK("");
    }

    @RequestMapping(value = "delete")
    public Response deletePurchaseContract(Long id) {
        repository.delete(id);
        return Response.OK("");
    }

    @RequestMapping(value = "queryAll")
    public Response queryAll() {
        List<PurchaseContractImpl> purchaseContract = repository.findAll();
        return Response.OK(purchaseContract);
    }
}
