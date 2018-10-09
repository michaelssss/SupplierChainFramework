package com.michaelssss.business.basicinfomanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.account.User;
import com.michaelssss.business.BusinessException;
import com.michaelssss.business.basicinfomanagement.Company;
import com.michaelssss.business.basicinfomanagement.Storage;
import com.michaelssss.business.basicinfomanagement.respository.StorageRepository;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "storage")
public class StorageImpl implements Storage, Serializable {

  private static final long serialVersionUID = -6099953598885627436L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
  private CompanyImpl company;

  private String applier;
  private Date applyDate;
  private Date auditDate;
  private String auditor;

  @Override
  public void apply(User user, Company company) {
    if (null == company) {
      throw new BusinessException("未查询到有效公司信息");
    }
    this.company = (CompanyImpl) company;
    this.applyDate = new Date();
    this.applier = user.getUsername();
    SpringContextHolder.getBean(StorageRepository.class).saveAndFlush(this);
  }
}
