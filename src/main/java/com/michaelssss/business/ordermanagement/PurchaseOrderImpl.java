package com.michaelssss.business.ordermanagement;

import com.michaelssss.SpringContextHolder;
import com.michaelssss.utils.BusinessCodeGenerator;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "purchase_order")
@Setter
@Getter
@ToString(callSuper = true)
@DiscriminatorValue("PURCHASE_ORDER")
class PurchaseOrderImpl extends AbstractOrder implements PurchaseOrder {

  private String entrustedCode; // 委托单编号
  private String currency; // 币种
  private Date deliveryDate; // 送达日期

  @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  private ReceivedInfo receivedInfo; // 收款人信息

  @Override
  protected String generateCode() {
    return SpringContextHolder.getBean(BusinessCodeGenerator.class)
        .getSequence(this.getClass(), "CG");
  }
}
