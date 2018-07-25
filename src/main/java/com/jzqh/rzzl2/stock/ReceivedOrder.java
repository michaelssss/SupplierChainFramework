package com.jzqh.rzzl2.stock;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "received_order",
        indexes = {@Index(name = "idx_purcharseordercodesuppliername", columnList = "purchaseOrderCode,supplierName")})
@Data
public class ReceivedOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String purchaseOrderCode;
    private String supplierName;
    private String deliverName;
    private String deliverDate;
    private String carNo;
    private String contactPhone;
    private String status;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, targetEntity = ReceivedOrderCargo.class)
    private List<ReceivedOrderCargo> cargos;

    public String getPurchaseOrderCode() {
        return purchaseOrderCode;
    }

    void addCargo(Cargo cargo) {
        this.cargos.add((ReceivedOrderCargo) cargo);
    }

    void receivedCargos(List<Cargo> cargos) {
        for (Cargo cargo : cargos) {
            this.cargos.get(this.cargos.indexOf(cargo)).setActual(cargo.getActual());
        }
    }

    void confirm() {
        this.status = "Confirm";
    }

    void terminate() {
        this.status = "Terminate";
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
