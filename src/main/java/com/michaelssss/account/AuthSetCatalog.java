package com.michaelssss.account;

import org.springframework.data.jpa.repository.JpaRepository;

interface AuthSetCatalog extends JpaRepository<GroupImpl, Long> {

}
