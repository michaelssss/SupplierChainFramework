package com.michaelssss.rzzl2.basicinfomanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.ProductionClassification;
import com.michaelssss.rzzl2.basicinfomanagement.domain.ProductionClassificationImpl;
import com.michaelssss.rzzl2.basicinfomanagement.service.ClassificationService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "Classification", method = RequestMethod.POST)
@Api(value = "分类管理", tags = "分类管理")
@SuppressWarnings("unchecked")
public class ClassificationController {

    private ClassificationService classificationService;

    @Autowired
    public ClassificationController(ClassificationService classificationService) {
        this.classificationService = classificationService;
    }

    @RequestMapping(value = "add")
    @ApiOperation(value = "添加分类", tags = "分类管理")
    @ResponseBody
    public Response<String> addClassification(@RequestBody ProductionClassification classification) {
        classification.save();
        return (Response<String>) Response.OK("添加分类成功");
    }

    @RequestMapping(value = "list")
    @ApiOperation(value = "获取分类列表", tags = "分类管理")
    @ResponseBody
    public Response<List<ProductionClassification>> list() {
        return (Response<List<ProductionClassification>>) Response.OK(classificationService.getLevelOneProductionClassification());
    }

    @RequestMapping(value = "SubClassification/add")
    @ApiOperation(value = "添加子分类", tags = "分类管理")
    @ResponseBody
    public Response<String> addSubClassification(@RequestBody ProductionClassificationDataBinder productionClassification) {
        ProductionClassification parent = classificationService.getById(productionClassification.getParentId());
        ProductionClassificationImpl productionClassification1 = new ProductionClassificationImpl();
        productionClassification1.setCode(productionClassification.getCode());
        productionClassification1.setName(productionClassification.getName());
        productionClassification1.setTemplateProperties(productionClassification.getTemplateProperties());
        productionClassification1.setChild(productionClassification.getChild());
        productionClassification1.setProductions(new ArrayList<>());
        parent.addSubClassification(productionClassification1);
        return (Response<String>) Response.OK("添加子分类成功");
    }
}
