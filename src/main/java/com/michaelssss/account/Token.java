package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(
    name = "sys_token",
    indexes = {@Index(name = "idx_token", columnList = "token,outdate", unique = true)})
public class Token {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonIgnore
  private Long uid;

  @Column(length = 64)
  private String token;

  private Date outdate;
  @OneToOne
  @JsonIgnore
  private UserImpl user;

  public boolean ifOutdate() {
    return new Date().compareTo(this.outdate) <= 0;
  }
}
