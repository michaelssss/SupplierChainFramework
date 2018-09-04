package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;

import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.SupplierImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SupplierRepository extends JpaRepository<SupplierImpl, Long> {
}
