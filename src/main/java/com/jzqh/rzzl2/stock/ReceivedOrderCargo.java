package com.jzqh.rzzl2.stock;

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
