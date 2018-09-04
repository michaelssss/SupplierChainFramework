package com.michaelssss.rzzl2.basicinfomanagement.customer.customerimpl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.Funding;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.FundingRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Builder
@Entity
@Data
@Table(name = "funding_party")
public class FundingPartyImpl implements Funding {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;

    @Override
    public void addInfo() {
        SpringContextHolder.getBean(FundingRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(FundingRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(FundingRepository.class).delete(this.Id);

    }
}
