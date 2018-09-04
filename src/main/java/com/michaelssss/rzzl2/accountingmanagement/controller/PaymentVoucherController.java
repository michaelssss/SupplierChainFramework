package com.michaelssss.rzzl2.accountingmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.accountingmanagement.PaymentVoucher;
import com.michaelssss.rzzl2.accountingmanagement.PaymentVoucherRepository;
import com.michaelssss.rzzl2.accountingmanagement.TransferReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

@Controller
@RequestMapping("PaymentVoucher")
public class PaymentVoucherController {

    @Autowired
    private PaymentVoucherRepository repository;

    @ResponseBody
    public Response addTransferReceipt(@RequestBody Map<String, TransferReceipt> map) {
        PaymentVoucher paymentVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        paymentVoucher.addTransferReceipt(map.get("transferReceipt"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response confirmAuditTotal(@RequestBody Map<String, BigDecimal> map) {
        PaymentVoucher paymentVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        paymentVoucher.confirmAuditTotal(map.get("auditTotal"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response getAuditTotal(@RequestParam(value = "id") Long id) {
        PaymentVoucher paymentVoucher = repository.findOne(id);
        return Response.OK(paymentVoucher.getAuditTotal());
    }

    @ResponseBody
    public Response getNotAuditTotal(@RequestParam(value = "id") Long id) {
        PaymentVoucher paymentVoucher = repository.findOne(id);
        return Response.OK(paymentVoucher.getNotAuditTotal());
    }

}