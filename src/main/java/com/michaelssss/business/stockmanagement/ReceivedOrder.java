package com.michaelssss.business.stockmanagement;

import java.math.BigDecimal;
import java.util.List;
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
import lombok.Data;

@Entity
@Table(
    name = "received_order",
    indexes = {
        @Index(
            name = "idx_purcharseordercodesuppliername",
            columnList = "purchaseOrderCode,supplierName")
    })
@Data
public class ReceivedOrder {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  @Column(length = 64)
  private String purchaseOrderCode;

  @Column(length = 64)
  private String supplierName;

  private String deliverName;
  private String deliverDate;
  private String carNo;
  private String contactPhone;
  private String status;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private List<Cargo> cargos;

  public String getPurchaseOrderCode() {
    return purchaseOrderCode;
  }

  void addCargo(Cargo cargo) {
    this.cargos.add(cargo);
  }

  void receivedCargos(List<Cargo> cargos) {
    for (Cargo cargo : cargos) {
      this.cargos.get(this.cargos.indexOf(cargo)).setActual(cargo.getActual());
    }
  }

  void confirm() {
    this.status = "CONFIRM";
  }

  void terminate() {
    this.status = "TERMINATE";
  }

  void supplierDeliveryConfirm() {
    this.status = "SupplierDeliveryConfirm";
  }

  BigDecimal getShouldTotal() {
    BigDecimal result = BigDecimal.ZERO;
    for (Cargo cargo : this.cargos) {
      result = result.add(cargo.getShould());
    }
    return result;
  }

  BigDecimal getActualTotal() {
    BigDecimal result = BigDecimal.ZERO;
    for (Cargo cargo : this.cargos) {
      result = result.add(cargo.getActual());
    }
    return result;
  }
}
