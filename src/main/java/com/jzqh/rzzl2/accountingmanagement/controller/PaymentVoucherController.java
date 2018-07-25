package com.jzqh.rzzl2.accountingmanagement.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.accountingmanagement.PaymentVoucherImpl;
import com.jzqh.rzzl2.accountingmanagement.PaymentVoucherRepository;
import com.jzqh.rzzl2.accountingmanagement.TransferReceipt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.math.BigDecimal;
import java.util.Map;

@Controller
public class PaymentVoucherController {

    @Autowired
    private PaymentVoucherRepository repository;

    @ResponseBody
    public Response addTransferReceipt(@RequestBody Map<String, TransferReceipt> map) {
        PaymentVoucherImpl paymentVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        paymentVoucher.addTransferReceipt(map.get("transferReceipt"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response confirmAuditTotal(@RequestBody Map<String, BigDecimal> map) {
        PaymentVoucherImpl paymentVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        paymentVoucher.confirmAuditTotal(map.get("auditTotal"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response getAuditTotal(@RequestParam(value = "id") Long id) {
        PaymentVoucherImpl paymentVoucher = repository.findOne(id);
        return Response.OK(paymentVoucher.getAuditTotal());
    }

    @ResponseBody
    public Response getNotAuditTotal(@RequestParam(value = "id") Long id) {
        PaymentVoucherImpl paymentVoucher = repository.findOne(id);
        return Response.OK(paymentVoucher.getNotAuditTotal());
    }

}
