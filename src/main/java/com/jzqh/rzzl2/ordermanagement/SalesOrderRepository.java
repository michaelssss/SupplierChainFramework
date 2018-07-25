package com.jzqh.rzzl2.ordermanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesOrderRepository extends JpaRepository<SalesOrderImpl, Long> {
}
