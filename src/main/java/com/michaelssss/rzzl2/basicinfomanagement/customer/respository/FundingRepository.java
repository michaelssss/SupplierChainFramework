package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;


import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.FundingPartyImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<FundingPartyImpl, Long> {
}
