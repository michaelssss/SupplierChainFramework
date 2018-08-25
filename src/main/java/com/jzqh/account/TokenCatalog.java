package com.jzqh.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenCatalog extends JpaRepository<Token, Long> {
}
