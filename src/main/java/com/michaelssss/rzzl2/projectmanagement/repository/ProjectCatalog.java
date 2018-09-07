package com.michaelssss.rzzl2.projectmanagement.repository;


import com.michaelssss.rzzl2.projectmanagement.impl.ProjectImpl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description:
 * @Author:tanshaoxing
 * @Date:2018/7/16
 */
public interface ProjectCatalog extends JpaRepository<ProjectImpl, Long> {
}
