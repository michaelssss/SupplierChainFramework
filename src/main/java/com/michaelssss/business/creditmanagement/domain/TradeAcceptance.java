package com.michaelssss.business.creditmanagement.domain;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.business.creditmanagement.Acceptance;
import com.michaelssss.business.creditmanagement.repository.TradeAcceptanceCatalog;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

/**
 * 商业承兑汇票
 */
@Table(name = "trade_acceptance")
@Entity
@Data
public class TradeAcceptance implements Acceptance {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  @ApiModelProperty(value = "票据编号")
  @Column(length = 64, unique = true)
  private String code;

  @ApiModelProperty(value = "票据金额")
  private BigDecimal amount;

  @ApiModelProperty(value = "出票日")
  private Date createDate;

  @ApiModelProperty(value = "到期日")
  private Date payDate;

  @ApiModelProperty(value = "开票企业")
  private String creator;

  @ApiModelProperty(value = "收款企业")
  private String receiver;

  @ApiModelProperty(value = "备注")
  private String remark;

  @Override
  public Acceptance apply() {
    return SpringContextHolder.getBean(TradeAcceptanceCatalog.class).saveAndFlush(this);
  }

  @Override
  public void transfer(String companyName, BigDecimal amount) {
  }

  @Override
  public void disCount() {}
}
