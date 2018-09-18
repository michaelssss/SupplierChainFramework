package com.michaelssss.business.riskmanagement;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@Table(name = "bcard_individual")
public class BCardIndividual implements Evaluate {
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
