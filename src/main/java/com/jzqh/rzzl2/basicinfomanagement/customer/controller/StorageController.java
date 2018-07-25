package com.jzqh.rzzl2.basicinfomanagement.customer.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.StorageImpl;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.StorageRepository;
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
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("update")
    public Response updateStorage(StorageImpl storage) {
        storage.updateInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("delete")
    public Response deleteStorage(StorageImpl storage) {
        storage.deleteInfo();
        return Response.OK(null);
    }

    @ResponseBody
    @RequestMapping("queryAll")
    public Response queryAllStorage() {
        List<StorageImpl> storages = storageRepository.findAll();
        return Response.OK(storages);
    }

    @ResponseBody
    @RequestMapping("queryInfo/id")
    public Response queryAllStorage(@RequestParam(value = "id") Long id) {
        StorageImpl storage = storageRepository.findOne(id);
        return Response.OK(storage);
    }


}
