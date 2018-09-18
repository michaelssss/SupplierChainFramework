package com.michaelssss.business.basicinfomanagement.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * 采购商准入申请
 */
@Builder
@Entity
@Data
@Table(name = "purchase_client_apply")
public class PurchaseClientApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    private Date applyDate;
    private Date auditDate;
    private String auditor;
}
