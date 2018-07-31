package com.jzqh.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuthSetCatalog extends JpaRepository<AuthoritiesSetImpl, Long> {

}
