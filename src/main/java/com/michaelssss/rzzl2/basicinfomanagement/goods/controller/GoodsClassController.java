package com.michaelssss.rzzl2.basicinfomanagement.goods.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.GoodsClassImpl;
import com.michaelssss.rzzl2.basicinfomanagement.goods.repository.GoodClassRepository;
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
@RequestMapping("GoodClass")
public class GoodsClassController {
    @Autowired
    private GoodClassRepository goodClassRepository;

    @ResponseBody
    @RequestMapping("add")
    public Response addGoodsClass(@RequestBody GoodsClassImpl goodsClass) {
        goodClassRepository.saveAndFlush(goodsClass);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateGoodsClass(@RequestBody GoodsClassImpl goodsClass) {
        goodClassRepository.saveAndFlush(goodsClass);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("delete/id")
    public Response deleteGoodsClass(@RequestParam(value = "id") Long id) {
        goodClassRepository.delete(id);
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryGoodsClassAll() {
        List<GoodsClassImpl> goodsClasses = goodClassRepository.findAll();
        return Response.OK(goodsClasses);
    }

    @ResponseBody
    @RequestMapping("query/id")
    public Response queryGoodsClassById(@RequestParam(value = "id") Long id) {
        GoodsClassImpl goodsClass = goodClassRepository.findOne(id);
        return Response.OK(goodsClass);
    }


}
