package com.michaelssss.business.creditmanagement.repository;

import com.michaelssss.business.creditmanagement.domain.TradeAcceptance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TradeAcceptanceCatalog extends JpaRepository<TradeAcceptance, Long> {
}
