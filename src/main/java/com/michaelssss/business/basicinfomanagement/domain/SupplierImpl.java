package com.michaelssss.business.basicinfomanagement.domain;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Supplier;
import com.michaelssss.business.basicinfomanagement.respository.SupplierRepository;
import lombok.Data;

import javax.persistence.*;


@Entity
@Data
@Table(name = "supplier")
public class SupplierImpl implements Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private SupplierClientApply supplierClientApply;

    @Override
    public void apply(SupplierClientApply supplierClientApply, Company company) {
        this.company = (CompanyImpl) company;
        this.supplierClientApply = supplierClientApply;
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);
    }
}

