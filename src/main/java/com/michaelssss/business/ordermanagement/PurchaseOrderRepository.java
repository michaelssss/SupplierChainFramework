package com.michaelssss.business.ordermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseOrderRepository extends JpaRepository<PurchaseOrderImpl, Long> {
}
