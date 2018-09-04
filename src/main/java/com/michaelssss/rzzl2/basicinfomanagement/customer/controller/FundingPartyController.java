package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl.FundingPartyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.FundingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/12
 */
@Controller
@RequestMapping("FundingParty")
public class FundingPartyController {
    @Autowired
    private FundingRepository fundingRepository;

    @ResponseBody
    @RequestMapping("add")
    public Response addFunding(FundingPartyImpl fundingParty) {
        fundingParty.addInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateFunding(FundingPartyImpl fundingParty) {
        fundingParty.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("delete")
    public Response deleteFunding(FundingPartyImpl fundingParty) {
        fundingParty.deleteInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryAllStorage() {
        List<FundingPartyImpl> fundingParty = fundingRepository.findAll();
        return Response.OK(fundingParty);
    }

    @ResponseBody
    @RequestMapping("queryInfo/id")
    public Response queryAllStorage(@RequestParam(value = "id") Long id) {
        FundingPartyImpl fundingParty = fundingRepository.findOne(id);
        return Response.OK(fundingParty);
    }
}
