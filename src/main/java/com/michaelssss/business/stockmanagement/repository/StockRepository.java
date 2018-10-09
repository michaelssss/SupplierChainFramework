package com.michaelssss.business.stockmanagement.repository;

import com.michaelssss.business.stockmanagement.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockImpl, Long> {

}
