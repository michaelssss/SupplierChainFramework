package com.michaelssss.rzzl2.ordermanagement;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Objects;

@Entity
@Table(name = "order_good")
@Data
public class OrderGood {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String name;//商品名称
    private String model;//型号
    private String brand;//品牌
    private String unit;//单位
    private BigDecimal unitPrice;//单位单价
    private String currency;//币种
    private BigDecimal total;//采购数量
    private String guaranteeRatio;//保证金比例
    private String address;//地址
    private String remark;//备注

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof OrderGood)) return false;
        OrderGood orderGood = (OrderGood) o;
        return Objects.equals(getName(), orderGood.getName()) &&
                Objects.equals(getModel(), orderGood.getModel()) &&
                Objects.equals(getBrand(), orderGood.getBrand()) &&
                Objects.equals(getUnit(), orderGood.getUnit()) &&
                Objects.equals(getUnitPrice(), orderGood.getUnitPrice()) &&
                Objects.equals(getCurrency(), orderGood.getCurrency()) &&
                Objects.equals(getTotal(), orderGood.getTotal()) &&
                Objects.equals(getGuaranteeRatio(), orderGood.getGuaranteeRatio()) &&
                Objects.equals(getAddress(), orderGood.getAddress()) &&
                Objects.equals(getRemark(), orderGood.getRemark());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getName(), getModel(), getBrand(), getUnit(), getUnitPrice(), getCurrency(), getTotal(), getGuaranteeRatio(), getAddress(), getRemark());
    }

}
