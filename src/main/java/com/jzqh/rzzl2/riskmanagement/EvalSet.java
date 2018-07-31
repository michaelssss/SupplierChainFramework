package com.jzqh.rzzl2.riskmanagement;

import java.math.BigDecimal;
import java.util.Collection;


public class EvalSet implements Evaluate {
    private Long uid;
    private String name;
    private Collection<Evaluate> evaluates;

    @Override
    public BigDecimal eval() {
        BigDecimal result = BigDecimal.ZERO;
        for (Evaluate evaluate : this.evaluates) {
            result.add(evaluate.eval());
        }
        return result;
    }
}
