package com.jzqh.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityCatalog extends JpaRepository<Authority, Long> {
}
