package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAccountRepository extends JpaRepository<BankAccount, Long> {
}
