package com.michaelssss.business.basicinfomanagement.domain;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.User;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Storage;
import com.michaelssss.business.basicinfomanagement.respository.StorageRepository;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "storage")
public class StorageImpl implements Storage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
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
        SpringContextHolder.getBean(StorageRepository.class).saveAndFlush(this);
    }
}
