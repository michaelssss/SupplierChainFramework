package com.michaelssss.rzzl2.contractmanagement.repository;

import com.michaelssss.rzzl2.contractmanagement.impl.PurchaseContractImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseContactRepository extends JpaRepository<PurchaseContractImpl, Long> {
}
