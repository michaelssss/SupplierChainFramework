package com.michaelssss.rzzl2.riskmanagement;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bcard_individual")
public class BCardIndividual extends AbstractEvaluate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String businessType;
    private String runningTime;
    private String operationStability;
    private String marketPosition;

    @Override
    public BigDecimal eval() {
        return null;
    }
}
