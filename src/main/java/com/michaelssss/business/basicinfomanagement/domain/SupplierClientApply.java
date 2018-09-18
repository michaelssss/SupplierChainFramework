package com.michaelssss.business.basicinfomanagement.domain;


import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Builder
@Entity
@Data
@Table(name = "supplier_client_apply")
public class SupplierClientApply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    private Date applyDate;
    private Date auditDate;
    private String auditor;
}
