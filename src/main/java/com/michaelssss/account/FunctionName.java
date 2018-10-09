package com.michaelssss.account;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;
import lombok.ToString;

/**
 * 将所有的东西归结于功能，比如能看到XXX功能的文件夹 具有审核XXX的功能
 */
@Entity
@Table(name = "sys_func")
@Data
@ToString
public class FunctionName {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @JsonIgnore
  private Long uid;

  private String functionName;
  @JsonIgnore
  private String url;

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof FunctionName)) {
      return false;
    }
    FunctionName that = (FunctionName) o;
    return Objects.equals(functionName, that.functionName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(functionName);
  }
}
