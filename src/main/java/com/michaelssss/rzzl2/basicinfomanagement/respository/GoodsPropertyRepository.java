package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsPropertyImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsPropertyRepository extends JpaRepository<GoodsPropertyImpl, Long> {
}
