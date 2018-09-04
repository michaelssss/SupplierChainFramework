package com.michaelssss.account;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorityCatalog extends JpaRepository<FunctionName, Long> {
}
