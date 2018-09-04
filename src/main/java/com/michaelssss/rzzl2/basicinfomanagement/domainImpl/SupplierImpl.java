package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.Supplier;
import com.michaelssss.rzzl2.basicinfomanagement.respository.SupplierRepository;
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

    @Override
    public void apply(Company company) {
        this.company = (CompanyImpl) company;
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);
    }
}

