package com.michaelssss.business.ordermanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

@Service
class OrderQueryImpl implements OrderQuery {

  private EntrustedOrderRepository entrustedOrderRepository;
  private PurchaseOrderRepository purchaseOrderRepository;
  private SalesOrderRepository salesOrderRepository;

  @Autowired
  public OrderQueryImpl(
      EntrustedOrderRepository entrustedOrderRepository,
      PurchaseOrderRepository purchaseOrderRepository,
      SalesOrderRepository salesOrderRepository) {
    this.entrustedOrderRepository = entrustedOrderRepository;
    this.purchaseOrderRepository = purchaseOrderRepository;
    this.salesOrderRepository = salesOrderRepository;
  }

  @Override
  public EntrustedOrder queryEntrustedOrderByEntrustedCode(String entrustedCode) {
    EntrustedOrderImpl entrustedOrder = new EntrustedOrderImpl();
    entrustedOrder.setCode(entrustedCode);
    return entrustedOrderRepository.findOne(Example.of(entrustedOrder)).get();
  }

  @Override
  public EntrustedOrder queryEntrustedOrderBySalesCode(String salesCode) {
    SalesOrderImpl salesOrder = new SalesOrderImpl();
    salesOrder.setCode(salesCode);
    salesOrder = salesOrderRepository.findOne(Example.of(salesOrder)).get();
    if (null == salesOrder) {
      return null;
    }
    EntrustedOrderImpl entrustedOrder = new EntrustedOrderImpl();
    entrustedOrder.setCode(salesOrder.getEntrustedCode());
    return entrustedOrderRepository.findOne(Example.of(entrustedOrder)).get();
  }

  @Override
  public EntrustedOrder queryEntrustedOrderByPurchaseCode(String purchaseCode) {
    PurchaseOrderImpl purchaseOrder = new PurchaseOrderImpl();
    purchaseOrder.setCode(purchaseCode);
    purchaseOrder = purchaseOrderRepository.findOne(Example.of(purchaseOrder)).get();
    if (null == purchaseOrder) {
      return null;
    }
    EntrustedOrderImpl entrustedOrder = new EntrustedOrderImpl();
    entrustedOrder.setCode(purchaseOrder.getEntrustedCode());
    return entrustedOrderRepository.findOne(Example.of(entrustedOrder)).get();
  }
}
