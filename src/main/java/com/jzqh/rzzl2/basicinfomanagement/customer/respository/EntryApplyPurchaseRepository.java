package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.EntryApplyPurchaseImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EntryApplyPurchaseRepository extends JpaRepository<EntryApplyPurchaseImpl, Long> {
}
