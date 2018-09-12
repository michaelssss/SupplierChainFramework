package com.michaelssss.business.creditmanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table
@Data
public class BankFlow {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String type;
    private String OrderCode;
    private BigDecimal occupation;
    private Date operationDate;
}
