package com.jzqh.rzzl2.basicinfomanagement.customer.respository;

import com.jzqh.rzzl2.basicinfomanagement.customer.customerimpl.ShareholderInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShareholderRepository extends JpaRepository<ShareholderInfo, Long> {
}
