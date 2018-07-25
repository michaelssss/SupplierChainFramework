package com.jzqh.rzzl2.basicinfomanagement.goods.repository;

import com.jzqh.rzzl2.basicinfomanagement.goods.impl.PropertyValueImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PropertyValueRepository extends JpaRepository<PropertyValueImpl, Long> {
}
