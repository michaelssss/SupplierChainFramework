package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.Storage;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.StorageRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "storage")
public class StorageImpl implements Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;


    @Override
    public void addInfo() {
        SpringContextHolder.getBean(StorageRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(StorageRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(StorageRepository.class).delete(this.id);

    }
}
