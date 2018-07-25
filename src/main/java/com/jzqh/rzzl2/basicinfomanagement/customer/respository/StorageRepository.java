package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.StorageImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<StorageImpl, Long> {
}
