package com.michaelssss.rzzl2.basicinfomanagement.customer.respository;

import com.michaelssss.rzzl2.basicinfomanagement.customer.domainImpl.ShareholderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareholderRepository extends JpaRepository<ShareholderInfo, Long> {
}
