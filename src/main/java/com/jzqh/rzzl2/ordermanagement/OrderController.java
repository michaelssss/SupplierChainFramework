package com.jzqh.rzzl2.ordermanagement;

import com.jzqh.base.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("Order")
public class OrderController {

   /* private EntrustedOrderRepository entrustedOrderRepository;
    private PurchaseOrderRepository purchaseOrderRepository;
    private SalesOrderRepository salesOrderRepository;

    @Autowired
    public OrderController(EntrustedOrderRepository entrustedOrderRepository, PurchaseOrderRepository purchaseOrderRepository, SalesOrderRepository salesOrderRepository) {
        this.entrustedOrderRepository = entrustedOrderRepository;
        this.purchaseOrderRepository = purchaseOrderRepository;
        this.salesOrderRepository = salesOrderRepository;
    }

    @RequestMapping("EntrustedOrder/add")
    @ResponseBody
    public Response addEntrustedOrder(EntrustedOrderImpl entrustedOrder) {
        this.entrustedOrderRepository.saveAndFlush(entrustedOrder);
        return Response.OK("");
    }

    @RequestMapping("PurchaseOrder/add")
    @ResponseBody
    public Response addPurchaseOrder(PurchaseOrderImpl purchaseOrder) {
        purchaseOrderRepository.saveAndFlush(purchaseOrder);
        return Response.OK("");
    }

    @RequestMapping("SalesOrder/add")
    @ResponseBody
    public Response addSalesOrder(SalesOrderImpl salesOrder) {
        salesOrderRepository.saveAndFlush(salesOrder);
        return Response.OK("");
    }*/

}
