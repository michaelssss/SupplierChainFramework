package com.michaelssss.rzzl2.stockmanagement.controller;

import com.michaelssss.base.Response;
import com.michaelssss.rzzl2.ordermanagement.EntrustedOrder;
import com.michaelssss.rzzl2.ordermanagement.OrderQuery;
import com.michaelssss.rzzl2.stockmanagement.DeliveryOrder;
import com.michaelssss.rzzl2.stockmanagement.ReceivedOrder;
import com.michaelssss.rzzl2.stockmanagement.StockImpl;
import com.michaelssss.rzzl2.stockmanagement.repository.StockRepository;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api()
@RequestMapping("Stock")
public class StockController {
    @Autowired
    OrderQuery orderQuery;
    private StockRepository repository;

    @Autowired
    public StockController(StockRepository repository) {
        this.repository = repository;
    }

    /**
     * 发货单
     *
     * @param deliveryOrder
     * @return
     */
    @RequestMapping("DeliveryOrder/add")
    @ResponseBody
    @ApiOperation(value = "xiaobai")
    public Response addDeliveryOrder(@ApiParam(name = "xiaozhuzhu") @RequestBody DeliveryOrder deliveryOrder, BindingResult result) {
        //TODO：从前端获取DeliveryOrder的JSON对象，并将对象委托Hibernate托管，并持久化，需要将对象所有字段都要填充，请勿填入NULL
        //TODO：请完成JUNIT的测试用例
        //TODO：请完成SWAGGER的API注释
        //1. 校验前端传入字段
        if (result.hasErrors()) {
            return Response.NonOK(null);
        }

        //2. 提交给StockRepository
        EntrustedOrder entrustedOrder = orderQuery.queryEntrustedOrderBySalesCode(deliveryOrder.getSaleOrderCode());
        if (entrustedOrder == null) {
            return Response.NonOK(null);
        }
        String entrustedOrderCode = entrustedOrder.getCode();

        // 使用Example快速实现动态查询
        StockImpl demo = new StockImpl();
        demo.setEntrustedOrderCode(entrustedOrderCode);
        Example<StockImpl> example = Example.of(demo);
        StockImpl stock = repository.findOne(example);

        if (stock == null) {
            stock = new StockImpl();
            stock.setEntrustedOrderCode(entrustedOrderCode);
            stock.DeliveryCargoOrderAdd(deliveryOrder);
        } else {
            stock.DeliveryCargoOrderAdd(deliveryOrder);
        }

        //3. 得到Hibernate托管并持久化对象
        stock = repository.saveAndFlush(stock);

        //4. 构造Response对象，并返回前端
        return Response.OK(stock);
    }

    /**
     * 收货单
     *
     * @param receivedOrder
     * @return
     */
    @RequestMapping("ReceivedOrder/add")
    @ResponseBody
    public Response addReceivedOrder(@RequestBody ReceivedOrder receivedOrder, BindingResult result) {
        //TODO：从前端获取ReceivedOrder的JSON对象，并将对象委托Hibernate托管，并持久化，需要将对象所有字段都要填充，请勿填入NULL
        //TODO：请完成JUNIT的测试用例
        //TODO：请完成SWAGGER的API注释
        //1. 校验前端传入字段
        if (result.hasErrors()) {
            return Response.NonOK(null);
        }
        //2. 提交给StockRepository
        EntrustedOrder entrustedOrder = orderQuery.queryEntrustedOrderByPurchaseCode(receivedOrder.getPurchaseOrderCode());
        if (entrustedOrder == null) {
            return Response.NonOK(null);
        }
        String entrustedOrderCode = entrustedOrder.getCode();

        StockImpl demo = new StockImpl();
        demo.setEntrustedOrderCode(entrustedOrderCode);
        Example<StockImpl> example = Example.of(demo);
        StockImpl stock = repository.findOne(example);

        if (stock == null) {
            stock = new StockImpl();
            stock.setEntrustedOrderCode(entrustedOrderCode);
            stock.ReceivedCargoOrderAdd(receivedOrder);

        } else {
            stock.ReceivedCargoOrderAdd(receivedOrder);

        }
        //3. 得到Hibernate托管并持久化对象
        stock = repository.saveAndFlush(stock);

        //4. 构造Response对象，并返回前端
        return Response.OK(stock);
    }
}
