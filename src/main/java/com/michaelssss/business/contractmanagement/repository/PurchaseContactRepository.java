package com.michaelssss.business.contractmanagement.repository;

import com.michaelssss.business.contractmanagement.impl.PurchaseContractImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseContactRepository extends JpaRepository<PurchaseContractImpl, Long> {

}
