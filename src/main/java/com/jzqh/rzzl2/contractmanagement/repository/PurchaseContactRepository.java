package com.jzqh.rzzl2.contractmanagement.repository;

import com.jzqh.rzzl2.contractmanagement.impl.PurchaseContractImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseContactRepository extends JpaRepository<PurchaseContractImpl, Long> {
}
