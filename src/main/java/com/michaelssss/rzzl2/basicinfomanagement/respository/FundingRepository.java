package com.michaelssss.rzzl2.basicinfomanagement.respository;


import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.FundingImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<FundingImpl, Long> {
}