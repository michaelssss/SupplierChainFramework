package com.michaelssss.rzzl2.ordermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EntrustedOrderRepository extends JpaRepository<EntrustedOrderImpl, Long> {
}