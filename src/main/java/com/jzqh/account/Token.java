package com.jzqh.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@Table(name = "sys_token", indexes = {@Index(name = "idx_token", columnList = "token,outdate", unique = true)})
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long uid;
    private String token;
    private Date outdate;
    @OneToOne
    @JsonIgnore
    private UserImpl user;

    public boolean isOutdate() {
        return new Date().compareTo(this.outdate) <= 0;
    }
}
