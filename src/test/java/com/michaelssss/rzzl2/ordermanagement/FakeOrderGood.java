package com.michaelssss.rzzl2.ordermanagement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collection;

public class FakeOrderGood {
    static long order = 0L;

    static OrderGood fakeOrderGood() {
        OrderGoodBuilder builder = OrderGoodBuilder.anOrderGood();
        builder.name("testGood" + order)
                .model("testModel" + order)
                .unit("testUnit")
                .unitPrice(BigDecimal.valueOf(0).add(BigDecimal.valueOf(order)))
                .total(BigDecimal.valueOf(20))
                .brand("testBrand")
                .currency("RMB")
                .address("testAddress")
                .guaranteeRatio("20%");
        order++;
        return builder.build();
    }

    static Collection<OrderGood> fakeOrderGoodList() {
        Collection<OrderGood> goods = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ((ArrayList<OrderGood>) goods).add(fakeOrderGood());
        }
        return goods;
    }
}
