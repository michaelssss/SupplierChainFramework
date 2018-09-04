package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;


import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.CompanyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.PurchaseClientApplyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.PurchaserImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.PurchaseClientApplyRepository;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("Purchaser")
public class PurchaserController {
    @Autowired
    PurchaserRepository purchaserRepository;
    @Autowired
    PurchaseClientApplyRepository applyPurchaseRepository;
    @Autowired
    private CompanyRepository repository;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addPurchaserInfo(@RequestBody PurchaserImpl purchaser) {
        CompanyImpl company = repository.findOne(purchaser.getCompany().getId());
        purchaser.setCompany(company);
        purchaser.addInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "addRequestPermit")
    public Response addPurchaserRequestPermit(PurchaseClientApplyImpl entryApplyPurchase) {
        entryApplyPurchase.requestPermit();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "requestPermit")
    public Response PurchaserRequestPermit(PurchaseClientApplyImpl entryApplyPurchase) {
        entryApplyPurchase.requestPermit();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "queryAllPermitInfo")
    public Response queryAllPermitInfo() {
        List<PurchaseClientApplyImpl> applyPurchaseList = applyPurchaseRepository.findAll();
        return Response.OK(applyPurchaseList);
    }

    @ResponseBody
    @RequestMapping(value = "permitId")
    public Response queryPermitInfoById(@RequestParam(value = "id") Long id) {
        PurchaseClientApplyImpl entryApplyPurchase = applyPurchaseRepository.findOne(id);
        return Response.OK(entryApplyPurchase);
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Response updatePurchaserInfo(PurchaserImpl purchaser) {
        purchaserRepository.saveAndFlush(purchaser);
        return Response.OK("");
    }

    @ResponseBody
    @DeleteMapping(value = "delete")
    public Response deletePurchaserInfo(@RequestParam(value = "id") Long id) {
        purchaserRepository.delete(id);
        return Response.OK("");
    }

    @ResponseBody
    @GetMapping(value = "queryAll")
    public Response queryPurchaserInfo() {
        List<PurchaserImpl> purchaserList = purchaserRepository.findAll();
        return Response.OK(purchaserList);
    }

    @ResponseBody
    @GetMapping(value = "query/id")
    public Response queryPurchaserInfoById(@RequestParam(value = "id") Long id) {
        PurchaserImpl purchaser = purchaserRepository.findOne(id);
        return Response.OK(purchaser);
    }


}
