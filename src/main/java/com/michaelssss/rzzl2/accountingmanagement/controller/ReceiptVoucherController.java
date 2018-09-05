package com.michaelssss.rzzl2.accountingmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.accountingmanagement.ReceiptVoucherImpl;
import com.michaelssss.rzzl2.accountingmanagement.ReceiptVoucherRepository;
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
@RequestMapping("ReceiptVoucher")
public class ReceiptVoucherController {

    @Autowired
    private ReceiptVoucherRepository repository;

    @ResponseBody
    public Response addTransferReceipt(@RequestBody Map<String, TransferReceipt> map) {
        ReceiptVoucherImpl receiptVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        receiptVoucher.addTransferReceipt(map.get("transferReceipt"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response confirmAuditTotal(@RequestBody Map<String, BigDecimal> map) {
        ReceiptVoucherImpl receiptVoucher = repository.findOne(Long.valueOf(map.get("id").toString()));
        receiptVoucher.confirmAuditTotal(map.get("auditTotal"));
        return Response.OK(null);
    }

    @ResponseBody
    public Response getAuditTotal(@RequestParam(value = "id") Long id) {
        ReceiptVoucherImpl receiptVoucher = repository.findOne(id);
        return Response.OK(receiptVoucher.getAuditTotal());
    }

    @ResponseBody
    public Response getNotAuditTotal(@RequestParam(value = "id") Long id) {
        ReceiptVoucherImpl receiptVoucher = repository.findOne(id);
        return Response.OK(receiptVoucher.getNotAuditTotal());
    }
}
