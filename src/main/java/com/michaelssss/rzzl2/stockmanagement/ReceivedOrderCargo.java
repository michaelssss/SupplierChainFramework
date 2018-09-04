package com.michaelssss.rzzl2.stockmanagement;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value = "ReceivedCargo")
public class ReceivedOrderCargo extends Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
}
