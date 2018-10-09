package com.michaelssss.business.stockmanagement;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;

@Entity
@Data
@DiscriminatorValue(value = "ReceivedCargo")
public class ReceivedOrderCargo extends Cargo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;
}
