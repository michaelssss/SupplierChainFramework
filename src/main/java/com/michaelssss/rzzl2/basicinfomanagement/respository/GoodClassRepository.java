package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.GoodsClassImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GoodClassRepository extends JpaRepository<GoodsClassImpl, Long> {
}
