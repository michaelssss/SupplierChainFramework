package com.jzqh.rzzl2.basicinfomanagement.customer.controller;


import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.CompanyImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplyPurchaseImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.PurchaserImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.CompanyRepository;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.EntryApplyPurchaseRepository;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.PurchaserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value = "Purchaser")
public class PurchaserController {
    @Autowired
    PurchaserRepository purchaserRepository;
    @Autowired
    EntryApplyPurchaseRepository applyPurchaseRepository;
    @Autowired
    private CompanyRepository repository;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addPurchaserInfo(@RequestBody PurchaserImpl purchaser) {
        CompanyImpl company = repository.findOne(purchaser.getCompany().getId());
        purchaser.setCompany(company);
        purchaser.addInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "add/requestPermit")
    public Response addPurchaserRequestPermit(EntryApplyPurchaseImpl entryApplyPurchase) {
        entryApplyPurchase.requestPermit();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "requestPermit")
    public Response PurchaserRequestPermit(EntryApplyPurchaseImpl entryApplyPurchase) {
        entryApplyPurchase.requestPermit();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "queryAll/permitInfo")
    public Response queryAllPermitInfo() {
        List<EntryApplyPurchaseImpl> applyPurchaseList = applyPurchaseRepository.findAll();
        return Response.OK(applyPurchaseList);
    }

    @ResponseBody
    @RequestMapping(value = "query/requestPermit/id")
    public Response queryPermitInfoById(@RequestParam(value = "id") Long id) {
        EntryApplyPurchaseImpl entryApplyPurchase = applyPurchaseRepository.findOne(id);
        return Response.OK(entryApplyPurchase);
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Response updatePurchaserInfo(PurchaserImpl purchaser) {
        purchaserRepository.saveAndFlush(purchaser);
        return Response.OK(null);
    }

    @ResponseBody
    @DeleteMapping(value = "delete")
    public Response deletePurchaserInfo(@RequestParam(value = "id") Long id) {
        purchaserRepository.delete(id);
        return Response.OK(null);
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
