package com.michaelssss.rzzl2.basicinfomanagement.goods.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.GoodsPropertyImpl;
import com.michaelssss.rzzl2.basicinfomanagement.goods.repository.GoodsPropertyRepository;
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
@RequestMapping("GoodsProperty")
public class GoodsPropertyController {
    @Autowired
    private GoodsPropertyRepository goodsPropertyRepository;

    @ResponseBody
    @RequestMapping("add")
    public Response addGoodsProperty(@RequestBody GoodsPropertyImpl goodsProperty) {
        goodsPropertyRepository.saveAndFlush(goodsProperty);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateGoodsProperty(@RequestBody GoodsPropertyImpl goodsProperty) {
        goodsPropertyRepository.saveAndFlush(goodsProperty);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("delete/id")
    public Response deleteGoodsProperty(@RequestParam(value = "id") Long id) {
        goodsPropertyRepository.delete(id);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("goodsProperty/queryAll")
    public Response queryGoodsProperty() {
        List<GoodsPropertyImpl> goodsProperties = goodsPropertyRepository.findAll();
        return Response.OK(goodsProperties);
    }

    @ResponseBody
    @RequestMapping("goodsProperty/id")
    public Response queryGoodsPropertyById(@RequestParam(value = "id") Long id) {
        GoodsPropertyImpl goodsProperty = goodsPropertyRepository.findOne(id);
        return Response.OK(goodsProperty);
    }
}
