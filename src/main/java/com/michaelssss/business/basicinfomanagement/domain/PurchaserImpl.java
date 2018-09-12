package com.michaelssss.business.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Purchaser;
import com.michaelssss.business.basicinfomanagement.respository.PurchaserRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Builder
@Table(name = "purchaser")
public class PurchaserImpl implements Purchaser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private PurchaseClientApply purchaseClientApply;

    @Override
    public void apply(PurchaseClientApply purchaseClientApply, Company company) {
        this.purchaseClientApply = purchaseClientApply;
        this.company = (CompanyImpl) company;
        SpringContextHolder.getBean(PurchaserRepository.class).saveAndFlush(this);
    }
}
