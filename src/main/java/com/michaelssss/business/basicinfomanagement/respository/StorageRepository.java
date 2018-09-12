package com.michaelssss.business.basicinfomanagement.respository;

import com.michaelssss.business.basicinfomanagement.domain.StorageImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<StorageImpl, Long> {
}
