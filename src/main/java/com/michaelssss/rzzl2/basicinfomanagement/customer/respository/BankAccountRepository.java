package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;

import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
