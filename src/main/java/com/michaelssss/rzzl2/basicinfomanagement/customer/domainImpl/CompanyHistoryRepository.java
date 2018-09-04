package com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyHistoryRepository extends JpaRepository<CompanyAuditHistoryEntity, Long> {
}
