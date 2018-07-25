package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
