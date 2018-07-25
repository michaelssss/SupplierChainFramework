package com.jzqh.rzzl2.basicinfomanagement.customer.respository;


import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.FundingPartyImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FundingRepository extends JpaRepository<FundingPartyImpl, Long> {
}
