package com.michaelssss.rzzl2.ordermanagement;

import java.math.BigDecimal;

public final class OrderGoodBuilder {
    private OrderGood orderGood;

    private OrderGoodBuilder() {
        orderGood = new OrderGood();
    }

    public static OrderGoodBuilder anOrderGood() {
        return new OrderGoodBuilder();
    }

    public OrderGoodBuilder name(String name) {
        orderGood.setName(name);
        return this;
    }

    public OrderGoodBuilder model(String model) {
        orderGood.setModel(model);
        return this;
    }

    public OrderGoodBuilder brand(String brand) {
        orderGood.setBrand(brand);
        return this;
    }

    public OrderGoodBuilder unit(String unit) {
        orderGood.setUnit(unit);
        return this;
    }

    public OrderGoodBuilder unitPrice(BigDecimal unitPrice) {
        orderGood.setUnitPrice(unitPrice);
        return this;
    }

    public OrderGoodBuilder currency(String currency) {
        orderGood.setCurrency(currency);
        return this;
    }

    public OrderGoodBuilder total(BigDecimal total) {
        orderGood.setTotal(total);
        return this;
    }

    public OrderGoodBuilder guaranteeRatio(String guaranteeRatio) {
        orderGood.setGuaranteeRatio(guaranteeRatio);
        return this;
    }

    public OrderGoodBuilder address(String address) {
        orderGood.setAddress(address);
        return this;
    }

    public OrderGoodBuilder remark(String remark) {
        orderGood.setRemark(remark);
        return this;
    }

    public OrderGood build() {
        return orderGood;
    }
}
