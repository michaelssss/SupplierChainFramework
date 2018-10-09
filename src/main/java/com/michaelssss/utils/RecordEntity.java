package com.michaelssss.utils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(
    name = "sequence_record",
    indexes = {@Index(name = "idx_prefixsequence", columnList = "prefix,sequence", unique = true)})
@Setter
@Getter
class RecordEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  private String clazzName;

  @Column(length = 64)
  private String prefix;

  private Long sequence;
}
