package com.michaelssss.rzzl2.creditmanagement.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "acceptance_transfer_log")
public class AcceptanceTransferLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String owner;
    private String newOwner;
    private String amount;
    private Date transferDate;
    //如果转让票据时候不做拆分，则新老的code应该是同一张
    private String originalCode;
    private String newCode;
}
