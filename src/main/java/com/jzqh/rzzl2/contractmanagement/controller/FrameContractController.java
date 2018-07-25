package com.jzqh.rzzl2.contractmanagement.controller;

import com.jzqh.base.Response;
import com.jzqh.rzzl2.contractmanagement.impl.FrameContractImpl;
import com.jzqh.rzzl2.contractmanagement.repository.FrameContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/19
 */
@Controller
@RequestMapping(path = "FrameContract")
public class FrameContractController {

    @Autowired
    private FrameContractRepository frameContractRepository;

    @RequestMapping(value = "add")
    public Response addFrameContract(FrameContractImpl frameContract) {
        frameContract.addFrameContract();
        return Response.OK("");
    }

    @RequestMapping(value = "update")
    public Response updateFrameContract(FrameContractImpl frameContract) {
        frameContract.updateFrameContract();
        return Response.OK("");
    }

    @RequestMapping(value = "delete")
    public Response deleteFrameContract(Long id) {
        frameContractRepository.delete(id);
        return Response.OK("");
    }

    @RequestMapping(value = "queryAll")
    public Response queryAll() {
        List<FrameContractImpl> frameContractList = frameContractRepository.findAll();
        return Response.OK(frameContractList);
    }
}
