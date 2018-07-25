package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplySupplierImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryApplySupplierRepository extends JpaRepository<EntryApplySupplierImpl, Long> {
}
