package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domain.ProductionClassification;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionClassificationCatalog extends JpaRepository<ProductionClassification, Long> {
}
