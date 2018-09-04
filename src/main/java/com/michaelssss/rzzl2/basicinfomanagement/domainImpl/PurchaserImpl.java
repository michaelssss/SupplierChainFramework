package com.michaelssss.rzzl2.basicinfomanagement.domainImpl;

import com.michaelssss.rzzl2.basicinfomanagement.Company;
import com.michaelssss.rzzl2.basicinfomanagement.Purchaser;
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

    @Override
    public void apply(Company company) {

    }
}
