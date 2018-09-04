package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.PurchaserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaserRepository extends JpaRepository<PurchaserImpl, Long> {
}
