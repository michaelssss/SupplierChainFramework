package com.michaelssss.business.creditmanagement.repository;

import com.michaelssss.business.creditmanagement.domain.CreditImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CreditRepository extends JpaRepository<CreditImpl, Long> {

}
