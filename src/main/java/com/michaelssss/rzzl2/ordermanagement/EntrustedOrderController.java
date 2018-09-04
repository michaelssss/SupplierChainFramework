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
@RequestMapping("EntrustedOrder")
public class EntrustedOrderController {

    private EntrustedOrderRepository entrustedOrderRepository;

    @Autowired
    public EntrustedOrderController(EntrustedOrderRepository entrustedOrderRepository) {
        this.entrustedOrderRepository = entrustedOrderRepository;
    }

    @RequestMapping("add")
    @ResponseBody
    public Response addEntrustedOrder(EntrustedOrderImpl entrustedOrder) {
        this.entrustedOrderRepository.saveAndFlush(entrustedOrder);
        return Response.OK("");
    }
    /**
     * 获取订单编号 生成新的编号
     * --String
     */
    @RequestMapping("getCode")
    public Response getCode(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.getCode();
        return Response.OK("");
    }

    /**
     * 获取订单状态
     * --String
     */
    @RequestMapping("getStatus")
    public Response getStatus(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.getStatus();
        return Response.OK("");
    }

    /**
     * 获取商品列表
     * --Collection<OrderGood>
     */
    @RequestMapping("getGoods")
    public Response getGoods(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.getGoods();
        return Response.OK("");
    }

    /**
     * 获取当前订单货品总价
     * --BigDecimal
     */
    @RequestMapping("getOrderGoodTotalPrice")
    public Response getOrderGoodTotalPrice(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.getOrderGoodTotalPrice();
        return Response.OK("");
    }

    /**
     * 委托单生效
     * --void
     */
    @RequestMapping("confirm")
    public Response confirm(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.confirm();
        return Response.OK("");
    }

    /**
     * 委托单终止
     * --void
     */
    @RequestMapping("terminate")
    public Response terminate(EntrustedOrderImpl entrustedOrder) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.terminate();
        return Response.OK("");
    }

    /**
     * 往未生效订单中添加商品
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("addOrderGood")
    public Response addOrderGood(EntrustedOrderImpl entrustedOrder, OrderGood good) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.addOrderGood(good);
        return Response.OK("");
    }

    /**
     * 从未生效商品列表中移除商品信息
     * --void
     * 如果订单已经生效了
     */
    @RequestMapping("removeOrderGood")
    public Response removeOrderGood(EntrustedOrderImpl entrustedOrder, OrderGood good) {
        EntrustedOrderImpl entrustedOrder1 = entrustedOrderRepository.findOne(Example.of(entrustedOrder));
        entrustedOrder1.removeOrderGood(good);
        return Response.OK("");
    }

}
