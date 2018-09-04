package com.michaelssss.rzzl2.stockmanagement.repository;

import com.michaelssss.rzzl2.stockmanagement.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockImpl, Long> {
}
