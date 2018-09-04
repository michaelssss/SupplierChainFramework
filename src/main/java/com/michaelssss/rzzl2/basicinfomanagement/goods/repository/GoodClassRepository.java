package com.michaelssss.rzzl2.basicinfomanagement.goods.repository;

import com.michaelssss.rzzl2.basicinfomanagement.goods.impl.GoodsClassImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodClassRepository extends JpaRepository<GoodsClassImpl, Long> {
}
