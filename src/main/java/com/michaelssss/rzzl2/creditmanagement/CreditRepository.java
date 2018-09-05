package com.michaelssss.rzzl2.creditmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditImpl, Long> {
}
