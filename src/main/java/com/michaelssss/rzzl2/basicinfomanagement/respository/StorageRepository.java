package com.michaelssss.rzzl2.basicinfomanagement.respository;

import com.michaelssss.rzzl2.basicinfomanagement.domainImpl.StorageImpl;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StorageRepository extends JpaRepository<StorageImpl, Long> {
}
