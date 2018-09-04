package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.CompanyAuditHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CompanyHistoryRepository extends JpaRepository<CompanyAuditHistoryEntity, Long> {
}
