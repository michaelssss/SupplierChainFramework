package com.michaelssss.rzzl2.basicinfomanagement.goods.repository;

import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.ProductionInfoImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<ProductionInfoImpl, Long> {
}
