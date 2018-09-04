package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "company_history", indexes = {@Index(name = "idx_companyNameHistoryId", columnList = "company_name,history_id", unique = true)})
@Data
public class CompanyAuditHistoryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(length = 64)
    private String historyId;
    @Column(length = 64)
    private String companyName;
    @OneToOne
    private CompanyImpl company;
}
