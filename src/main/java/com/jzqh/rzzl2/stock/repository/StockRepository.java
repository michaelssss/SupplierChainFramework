package com.jzqh.rzzl2.stock.repository;

import com.jzqh.rzzl2.stock.StockImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepository extends JpaRepository<StockImpl, Long> {
}
