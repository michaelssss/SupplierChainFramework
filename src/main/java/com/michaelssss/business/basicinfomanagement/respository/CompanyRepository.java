package com.michaelssss.business.basicinfomanagement.respository;

import com.michaelssss.business.basicinfomanagement.domain.CompanyImpl;
import org.springframework.data.jpa.repository.JpaRepository;


public interface CompanyRepository extends JpaRepository<CompanyImpl, Long> {
}
