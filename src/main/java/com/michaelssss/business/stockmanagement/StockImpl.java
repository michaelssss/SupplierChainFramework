package com.michaelssss.business.stockmanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import lombok.Data;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(
    name = "stockmanagement",
    indexes = @Index(name = "idx_code", columnList = "code", unique = true),
    uniqueConstraints = {@UniqueConstraint(columnNames = "entrustedOrderCode")})
@Data
public class StockImpl implements Stock {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;
  // 业务主键
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(length = 64)
  private String code; // 业务主键，也可以称为仓库编号
  // 委托单编号，唯一
  @Column(length = 64)
  private String entrustedOrderCode;
  // 发货单
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  private List<DeliveryOrder> deliveryOrders = new ArrayList<>(); // 发货单列表
  // 收货单
  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @Fetch(FetchMode.SUBSELECT)
  private List<ReceivedOrder> receivedOrders = new ArrayList<>(); // 收货单列表

  @Override
  public void receivedCargoOrderAdd(ReceivedOrder receivedOrder) {
    this.receivedOrders.add(receivedOrder);
  }

  @Override
  public void deliveryCargoOrderAdd(DeliveryOrder deliveryOrder) {
    this.deliveryOrders.add(deliveryOrder);
  }

  /**
   * 获取仓库内货物的存量信息 若只有发货单没有收货单，相应物品的数量应为负数
   */
  Collection<Cargo> getStockCargo() {
    Set<Cargo> cargos = new HashSet<>();
    for (ReceivedOrder receivedOrder : this.receivedOrders) {
      cargos.addAll(receivedOrder.getCargos());
    }
    for (DeliveryOrder deliveryOrder : this.deliveryOrders) {
      for (Cargo deliveryOrderCargo : deliveryOrder.getCargos()) {
        if (cargos.contains(deliveryOrderCargo)) {
          for (Cargo cargo : cargos) {
            if (cargo.equals(deliveryOrderCargo)) {
              cargo.setActual(cargo.getActual().subtract(deliveryOrderCargo.getActual()));
            }
          }
        } else {
          deliveryOrderCargo.setActual(
              deliveryOrderCargo.getActual().multiply(BigDecimal.valueOf(-1)));
          deliveryOrderCargo.setShould(
              deliveryOrderCargo.getShould().multiply(BigDecimal.valueOf(-1)));
          cargos.add(deliveryOrderCargo);
        }
      }
    }
    return cargos;
  }
}
