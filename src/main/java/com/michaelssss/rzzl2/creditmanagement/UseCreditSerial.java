package com.michaelssss.rzzl2.creditmanagement;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "use_credit_serial")
@Data
public class UseCreditSerial {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String type;
    private String OrderCode;
    private BigDecimal occupation;
    private Date operationDate;
}
