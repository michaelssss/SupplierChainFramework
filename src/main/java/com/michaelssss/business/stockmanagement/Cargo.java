package com.michaelssss.business.stockmanagement;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

/**
 * 货物基类，表示一个发货单或者一个收货单中，货品信息列表中的货品信息
 * 注意到一个货品应该由货品名称，型号，品牌，单位唯一确定，若有一个不同则认为是不同货品
 */
@Entity
@Table(name = "cargo", indexes = {@Index(name = "idx_namemodelbrand", columnList = "name,model,brand")})
@Data
public class Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 64)
    private String name = "";//货品名称
    @Column(length = 64)
    private String model = "";//货品型号
    @Column(length = 64)
    private String brand = "";//货品品牌
    private BigDecimal should = BigDecimal.ZERO;//应收
    private BigDecimal actual = BigDecimal.ZERO;//实收
    private String unit = "";//货物发送单位，例如箱，瓶

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Cargo)) return false;
        Cargo cargo = (Cargo) o;
        return Objects.equals(getName(), cargo.getName()) &&
                Objects.equals(getModel(), cargo.getModel()) &&
                Objects.equals(getBrand(), cargo.getBrand()) &&
                Objects.equals(getUnit(), cargo.getUnit());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getModel(), getBrand(), getUnit());
    }
}
