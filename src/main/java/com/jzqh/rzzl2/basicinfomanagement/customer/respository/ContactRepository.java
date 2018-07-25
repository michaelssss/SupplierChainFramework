package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

}
