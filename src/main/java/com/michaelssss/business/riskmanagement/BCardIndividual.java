package com.michaelssss.business.riskmanagement;

import java.math.BigDecimal;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

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
