package com.michaelssss.rzzl2.basicinfomanagement.goods.repository;

import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.GoodsPropertyImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodsPropertyRepository extends JpaRepository<GoodsPropertyImpl, Long> {
}
