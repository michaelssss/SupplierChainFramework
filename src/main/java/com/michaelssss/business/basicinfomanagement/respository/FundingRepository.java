package com.michaelssss.business.basicinfomanagement.respository;


import com.michaelssss.business.basicinfomanagement.domain.FundingImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<FundingImpl, Long> {
}
