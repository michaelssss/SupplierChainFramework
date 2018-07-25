package com.jzqh.utils;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "sequence_record",
        indexes = {@Index(name = "idx_prefixsequence", columnList = "prefix,sequence", unique = true)})
@Setter
@Getter
class RecordEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    private String clazzName;
    private String prefix;
    private Long sequence;
}
