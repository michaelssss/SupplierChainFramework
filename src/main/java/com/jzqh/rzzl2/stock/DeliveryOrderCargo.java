package com.jzqh.rzzl2.stock;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@DiscriminatorValue(value = "DeliverCargo")
public class DeliveryOrderCargo extends Cargo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
}
