package com.michaelssss.business.contractmanagement.repository;

import com.michaelssss.business.contractmanagement.impl.SalesContractImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesContractRepository extends JpaRepository<SalesContractImpl, Long> {

}
