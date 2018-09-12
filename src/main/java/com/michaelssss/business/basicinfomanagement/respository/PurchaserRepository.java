package com.michaelssss.business.basicinfomanagement.respository;

import com.michaelssss.business.basicinfomanagement.domain.PurchaserImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaserRepository extends JpaRepository<PurchaserImpl, Long> {
}
