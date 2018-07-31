package com.jzqh.rzzl2.basicinfomanagement.goods.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.goods.impl.ProductionInfoImpl;
import com.jzqh.rzzl2.basicinfomanagement.goods.repository.GoodClassRepository;
import com.jzqh.rzzl2.basicinfomanagement.goods.repository.ProductionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/11
 */
@Controller
@RequestMapping("GoodInfo")
public class GoodInfoController {
    @Autowired
    private ProductionRepository productionRepository;
    @Autowired
    private GoodClassRepository goodClassRepository;

    @ResponseBody
    @RequestMapping("add")
    public Response addGoods(@RequestBody ProductionInfoImpl productionInfo) {
        productionInfo.setGoodsClass(goodClassRepository.findOne(productionInfo.getGoodsClass().getId()));
        productionRepository.saveAndFlush(productionInfo);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateGoods(@RequestBody ProductionInfoImpl productionInfo) {
        productionRepository.saveAndFlush(productionInfo);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("delete/id")
    public Response deleteGoods(@RequestParam(value = "id") Long id) {
        productionRepository.delete(id);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryGoodsAll() {
        List<ProductionInfoImpl> productionInfos = productionRepository.findAll();
        return Response.OK(productionInfos);
    }

    @ResponseBody
    @RequestMapping("query/id")
    public Response queryGoodsById(@RequestParam(value = "id") Long id) {
        ProductionInfoImpl productionInfo = productionRepository.findOne(id);
        return Response.OK(productionInfo);
    }


}
