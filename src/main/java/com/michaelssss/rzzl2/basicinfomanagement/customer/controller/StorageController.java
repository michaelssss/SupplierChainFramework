package com.michaelssss.rzzl2.basicinfomanagement.customer.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.StorageImpl;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.StorageRepository;
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
@RequestMapping("Storage")
public class StorageController {

    @Autowired
    private StorageRepository storageRepository;

    @ResponseBody
    @RequestMapping("add")
    public Response addStorage(StorageImpl storage) {
        storage.addInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateStorage(StorageImpl storage) {
        storage.updateInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("delete")
    public Response deleteStorage(StorageImpl storage) {
        storage.deleteInfo();
        return Response.OK("");
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryAllStorage() {
        List<StorageImpl> storages = storageRepository.findAll();
        return Response.OK(storages);
    }

    @ResponseBody
    @RequestMapping("queryInfo")
    public Response queryAllStorage(@RequestParam(value = "id") Long id) {
        StorageImpl storage = storageRepository.findOne(id);
        return Response.OK(storage);
    }


}
