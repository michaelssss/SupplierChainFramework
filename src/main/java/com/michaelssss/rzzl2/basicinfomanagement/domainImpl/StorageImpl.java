package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.Storage;
import com.michaelssss.rzzl2.basicinfomanagement.respository.StorageRepository;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "storage")
public class StorageImpl implements Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private CompanyImpl company;

    @Override
    public void apply(Company company) {
        this.company = (CompanyImpl) company;
        SpringContextHolder.getBean(StorageRepository.class).saveAndFlush(this);
    }
}
