package com.michaelssss.configuration;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;

import javax.persistence.*;

@Slf4j
@Entity
@Table(name = "sys_config_key_value")
@Data
@EqualsAndHashCode
class ConfigurationKeyValueObject {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;
    @Column(name = "`key`")
    private String key;
    @Column(name = "`value`")
    private String value;
}