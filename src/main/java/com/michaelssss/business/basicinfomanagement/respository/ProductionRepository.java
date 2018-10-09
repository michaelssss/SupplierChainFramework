package com.michaelssss.business.basicinfomanagement.respository;

import com.michaelssss.business.basicinfomanagement.domain.ProductionImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductionRepository extends JpaRepository<ProductionImpl, Long> {

}
