package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domain.ProductionClassificationImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionClassificationCatalog extends JpaRepository<ProductionClassificationImpl, Long> {
}
