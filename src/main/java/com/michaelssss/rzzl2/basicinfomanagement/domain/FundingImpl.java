package com.michaelssss.rzzl2.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.Funding;
import com.michaelssss.rzzl2.basicinfomanagement.respository.FundingRepository;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "funding")
public class FundingImpl implements Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;

    @Override
    public void apply(Company company) {
        this.company = (CompanyImpl) company;
        SpringContextHolder.getBean(FundingRepository.class).saveAndFlush(this);
    }
}
