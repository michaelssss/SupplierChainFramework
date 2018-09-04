package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;

import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.PurchaserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaserRepository extends JpaRepository<PurchaserImpl, Long> {
}
