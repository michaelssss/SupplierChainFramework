package com.michaelssss.rzzl2.projectmanagement.repository;

import com.michaelssss.rzzl2.projectmanagement.impl.ApprovalImpl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
public interface ApprovalCatalog extends JpaRepository<ApprovalImpl, Long> {
}
