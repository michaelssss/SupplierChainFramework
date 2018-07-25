package com.jzqh.rzzl2.contractmanagement.repository;

import com.jzqh.rzzl2.contractmanagement.impl.SalesContractImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesContractRepository extends JpaRepository<SalesContractImpl, Long> {
}
