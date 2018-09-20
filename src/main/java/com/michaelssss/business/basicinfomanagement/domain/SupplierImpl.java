package com.michaelssss.business.basicinfomanagement.domain;


import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.User;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Supplier;
import com.michaelssss.business.basicinfomanagement.respository.SupplierRepository;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;


@Entity
@Data
@Table(name = "supplier")
public class SupplierImpl implements Supplier, Serializable {

    private static final long serialVersionUID = -4741104850882056110L;
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
        if (null == company) {
            throw new BusinessException("未查询到有效公司信息");
        }
        this.company = (CompanyImpl) company;
        this.applyDate = new Date();
        this.applier = user.getUsername();
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);
    }
}

