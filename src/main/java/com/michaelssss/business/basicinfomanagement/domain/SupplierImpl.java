package com.michaelssss.business.basicinfomanagement.domain;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.User;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Supplier;
import com.michaelssss.business.basicinfomanagement.respository.SupplierRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;


@Entity
@Data
@Table(name = "supplier")
public class SupplierImpl implements Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    private String applier;
    private Date applyDate;
    private Date auditDate;
    private String auditor;

    @Override
    public void apply(User user, Company company) {
        this.company = (CompanyImpl) company;
        this.applyDate = new Date();
        this.applier = user.getUsername();
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);
    }
}

