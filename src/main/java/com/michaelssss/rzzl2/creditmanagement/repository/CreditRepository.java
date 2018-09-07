package com.michaelssss.rzzl2.creditmanagement.repository;

import com.michaelssss.rzzl2.creditmanagement.domain.CreditImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditImpl, Long> {
}
