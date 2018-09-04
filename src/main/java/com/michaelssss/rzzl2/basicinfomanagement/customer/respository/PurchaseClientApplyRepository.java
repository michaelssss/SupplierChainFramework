package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;

import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.PurchaseClientApplyImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseClientApplyRepository extends JpaRepository<PurchaseClientApplyImpl, Long> {
}
