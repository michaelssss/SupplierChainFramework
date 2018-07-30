package com.jzqh.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuthorityCatalog extends JpaRepository<Authority, Long> {
}
