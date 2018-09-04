package com.michaelssss.rzzl2.ordermanagement;

import com.michaelssss.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/23 10:44
 */

@Controller
@RequestMapping("SalesOrder")
public class SalesOrderController {

    private SalesOrderRepository salesOrderRepository;

    @Autowired
    public SalesOrderController(SalesOrderRepository salesOrderRepository) {
        this.salesOrderRepository = salesOrderRepository;
    }

    @RequestMapping("SalesOrder/add")
    @ResponseBody
    public Response addSalesOrder(SalesOrderImpl salesOrder) {
        salesOrderRepository.saveAndFlush(salesOrder);
        return Response.OK("");
    }
    /**
     * 获取订单编号
     * --String
     */
    @RequestMapping("getCode")
    public Response getCode(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.getCode();
        return Response.OK("");
    }

    /**
     * 获取订单状态
     * --String
     */

    @RequestMapping("getStatus")
    public Response getStatus(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.getStatus();
        return Response.OK("");
    }

    /**
     * 获取商品列表
     * --Collection<OrderGood>
     */
    @RequestMapping("getGoods")
    public Response getGoods(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.getGoods();
        return Response.OK("");
    }

    /**
     * 获取当前订单货品总价
     * --BigDecimal
     */
    @RequestMapping("getOrderGoodTotalPrice")
    public Response getOrderGoodTotalPrice(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.getOrderGoodTotalPrice();
        return Response.OK("");
    }

    /**
     * 委托单生效
     * --void
     */
    @RequestMapping("confirm")
    public Response confirm(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.confirm();
        return Response.OK("");
    }

    /**
     * 委托单终止
     * --void
     */
    @RequestMapping("terminate")
    public Response terminate(SalesOrderImpl salesOrder) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.terminate();
        return Response.OK("");
    }

    /**
     * 往未生效订单中添加商品
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("addOrderGood")
    public Response addOrderGood(SalesOrderImpl salesOrder, OrderGood good) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.addOrderGood(good);
        return Response.OK("");
    }

    /**
     * 从未生效商品列表中移除商品信息
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("removeOrderGood")
    public Response removeOrderGood(SalesOrderImpl salesOrder, OrderGood good) {
        SalesOrderImpl salesOrder1 = salesOrderRepository.findOne(Example.of(salesOrder));
        salesOrder1.removeOrderGood(good);
        return Response.OK("");
    }

}
