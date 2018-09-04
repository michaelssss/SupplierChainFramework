package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.rzzl2.basicinfomanagement.customer.Purchaser;
import com.michaelssss.rzzl2.basicinfomanagement.customer.respository.PurchaserRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;

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
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<PurchaseClientApplyImpl> entryApplyPurchaseSet;

    @Override
    public void addInfo() {
        SpringContextHolder.getBean(PurchaserRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(PurchaserRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(PurchaserRepository.class).delete(this.id);

    }

    @Override
    public void requestPermit() {

    }

    @Override
    public void addPermitInfo(PurchaseClientApplyImpl entryApplyPurchase) {
        this.entryApplyPurchaseSet.add(entryApplyPurchase);
    }
}
