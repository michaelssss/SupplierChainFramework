package com.michaelssss.business.basicinfomanagement.respository;

import com.michaelssss.business.basicinfomanagement.domain.ProductionClassificationImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionClassificationCatalog
    extends JpaRepository<ProductionClassificationImpl, Long> {

}
