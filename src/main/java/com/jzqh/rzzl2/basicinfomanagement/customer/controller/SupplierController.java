package com.jzqh.rzzl2.basicinfomanagement.customer.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplySupplierImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.SupplierImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.EntryApplySupplierRepository;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/12
 */
@Controller
@RequestMapping(value = "Supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private EntryApplySupplierRepository entryApplySupplierRepository;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addPurchaserInfo(SupplierImpl supplier) {
        supplier.addInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Response updatePurchaserInfo(SupplierImpl supplier) {
        supplier.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @DeleteMapping(value = "delete")
    public Response deletePurchaserInfo(@RequestParam(value = "id") Long id) {
        supplierRepository.delete(id);
        return Response.OK(null);
    }

    @ResponseBody
    @GetMapping(value = "queryAll")
    public Response queryPurchaserInfo() {
        List<SupplierImpl> supplierList = supplierRepository.findAll();
        return Response.OK(supplierList);
    }

    @ResponseBody
    @GetMapping(value = "query/id")
    public Response queryPurchaserInfoById(@RequestParam(value = "id") Long id) {
        SupplierImpl supplier = supplierRepository.findOne(id);
        return Response.OK(supplier);
    }

    @ResponseBody
    @RequestMapping(value = "add/requestPermit")
    public Response addPurchaserRequestPermit(EntryApplySupplierImpl entryApplySupplier) {
        entryApplySupplier.addInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "requestPermit")
    public Response PurchaserRequestPermit(EntryApplySupplierImpl entryApplySupplier) {
        entryApplySupplier.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping(value = "queryAll/permitInfo")
    public Response queryAllPermitInfo() {
        List<EntryApplySupplierImpl> applySupplierList = entryApplySupplierRepository.findAll();
        return Response.OK(applySupplierList);
    }

    @ResponseBody
    @RequestMapping(value = "query/requestPermit/id")
    public Response queryPermitInfoById(@RequestParam(value = "id") Long id) {
        EntryApplySupplierImpl applySupplierList = entryApplySupplierRepository.findOne(id);
        return Response.OK(applySupplierList);
    }
}
