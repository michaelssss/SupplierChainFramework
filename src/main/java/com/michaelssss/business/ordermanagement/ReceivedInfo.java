package com.michaelssss.business.ordermanagement;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 收款人信息
 */
@Entity
@Table(name = "received_info")
@Data
public class ReceivedInfo {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  private String companyFullName; // 公司全称
  private String companyAddress; // 公司地址
  private String bankName; // 银行名称
  private String bankAddress; // 银行地址
  private String bankAccount; // 银行账户
  private String remark; // 备注

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof ReceivedInfo)) {
      return false;
    }
    ReceivedInfo that = (ReceivedInfo) o;
    return Objects.equals(getCompanyFullName(), that.getCompanyFullName())
        && Objects.equals(getCompanyAddress(), that.getCompanyAddress())
        && Objects.equals(getBankName(), that.getBankName())
        && Objects.equals(getBankAddress(), that.getBankAddress())
        && Objects.equals(getBankAccount(), that.getBankAccount())
        && Objects.equals(getRemark(), that.getRemark());
  }

  @Override
  public int hashCode() {

    return Objects.hash(
        getCompanyFullName(),
        getCompanyAddress(),
        getBankName(),
        getBankAddress(),
        getBankAccount(),
        getRemark());
  }
}
