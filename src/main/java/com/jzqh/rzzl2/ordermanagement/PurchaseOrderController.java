package com.jzqh.rzzl2.ordermanagement;

import com.jzqh.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * @author Tongch
 * @version 1.0
 * @time 2018/7/23 10:44
 */

@Controller
@RequestMapping("PurchaseOrder")
public class PurchaseOrderController {

    private PurchaseOrderRepository purchaseOrderRepository;

    @Autowired
    public PurchaseOrderController(PurchaseOrderRepository purchaserRepository) {
        this.purchaseOrderRepository = purchaserRepository;
    }

    /**
     * 获取订单编号
     * --String
     */
    @RequestMapping("getCode")
    public Response getCode(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.getCode();
        return Response.OK("");
    }

    /**
     * 获取订单状态
     * --String
     */

    @RequestMapping("getStatus")
    public Response getStatus(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.getStatus();
        return Response.OK("");
    }

    /**
     * 获取商品列表
     * --Collection<OrderGood>
     */
    @RequestMapping("getGoods")
    public Response getGoods(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.getGoods();
        return Response.OK("");
    }

    /**
     * 获取当前订单货品总价
     * --BigDecimal
     */
    @RequestMapping("getOrderGoodTotalPrice")
    public Response getOrderGoodTotalPrice(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.getOrderGoodTotalPrice();
        return Response.OK("");
    }

    /**
     * 委托单生效
     * --void
     */
    @RequestMapping("confirm")
    public Response confirm(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.confirm();
        return Response.OK("");
    }

    /**
     * 委托单终止
     * --void
     */
    @RequestMapping("terminate")
    public Response terminate(PurchaseOrderImpl purchaseOrder) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.terminate();
        return Response.OK("");
    }

    /**
     * 往未生效订单中添加商品
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("addOrderGood")
    public Response addOrderGood(PurchaseOrderImpl purchaseOrder, OrderGood good) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.addOrderGood(good);
        return Response.OK("");
    }

    /**
     * 从未生效商品列表中移除商品信息
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("removeOrderGood")
    public Response removeOrderGood(PurchaseOrderImpl purchaseOrder, OrderGood good) {
        PurchaseOrderImpl purchaseOrder1 = purchaseOrderRepository.findOne(Example.of(purchaseOrder));
        purchaseOrder1.removeOrderGood(good);
        return Response.OK("");
    }

}
