package com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl;


import com.jzqh.SpringContextHolder;
import com.jzqh.rzzl2.basicinfomanagement.customer.Supplier;
import com.jzqh.rzzl2.basicinfomanagement.customer.respository.SupplierRepository;
import lombok.Builder;
import lombok.Data;

import javax.persistence.*;
import java.util.Set;


@Builder
@Entity
@Data
@Table(name = "supplier")
public class SupplierImpl implements Supplier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private CompanyImpl company;
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<EntryApplySupplierImpl> entryApplySuppliersSet;

    @Override
    public void addInfo() {
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);

    }

    @Override
    public void updateInfo() {
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);

    }

    @Override
    public void deleteInfo() {
        SpringContextHolder.getBean(SupplierRepository.class).delete(this.id);

    }

    @Override
    public void requestPermit() {
        SpringContextHolder.getBean(SupplierRepository.class).saveAndFlush(this);
    }


}
