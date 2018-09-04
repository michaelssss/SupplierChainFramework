package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplySupplierImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.SupplierImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.EntryApplySupplierRepository;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.SupplierRepository;
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
@RequestMapping("Supplier")
public class SupplierController {
    @Autowired
    private SupplierRepository supplierRepository;
    @Autowired
    private EntryApplySupplierRepository entryApplySupplierRepository;

    @ResponseBody
    @RequestMapping(value = "add", method = RequestMethod.POST)
    public Response addPurchaserInfo(SupplierImpl supplier) {
        supplier.addInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "update")
    public Response updatePurchaserInfo(SupplierImpl supplier) {
        supplier.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @DeleteMapping(value = "delete")
    public Response deletePurchaserInfo(@RequestParam(value = "id") Long id) {
        supplierRepository.delete(id);
        return Response.OK("");
    }

    @ResponseBody
    @GetMapping(value = "queryAll")
    public Response queryPurchaserInfo() {
        List<SupplierImpl> supplierList = supplierRepository.findAll();
        return Response.OK(supplierList);
    }

    @ResponseBody
    @GetMapping(value = "query")
    public Response queryPurchaserInfoById(@RequestParam(value = "id") Long id) {
        SupplierImpl supplier = supplierRepository.findOne(id);
        return Response.OK(supplier);
    }

    @ResponseBody
    @RequestMapping(value = "addRequestPermit")
    public Response addPurchaserRequestPermit(EntryApplySupplierImpl entryApplySupplier) {
        entryApplySupplier.addInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "requestPermit")
    public Response PurchaserRequestPermit(EntryApplySupplierImpl entryApplySupplier) {
        entryApplySupplier.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping(value = "queryAllPermitInfo")
    public Response queryAllPermitInfo() {
        List<EntryApplySupplierImpl> applySupplierList = entryApplySupplierRepository.findAll();
        return Response.OK(applySupplierList);
    }

    @ResponseBody
    @RequestMapping(value = "requestPermitId")
    public Response queryPermitInfoById(@RequestParam(value = "id") Long id) {
        EntryApplySupplierImpl applySupplierList = entryApplySupplierRepository.findOne(id);
        return Response.OK(applySupplierList);
    }
}
