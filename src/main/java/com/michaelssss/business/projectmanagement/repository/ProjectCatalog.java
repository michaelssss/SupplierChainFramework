package com.michaelssss.business.projectmanagement.repository;

import com.michaelssss.business.projectmanagement.impl.ProjectImpl;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @Description: @Author:tanshaoxing @Date:2018/7/16
 */
public interface ProjectCatalog extends JpaRepository<ProjectImpl, Long> {

}
