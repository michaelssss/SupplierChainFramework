package com.michaelssss.business.accountingmanagement;

import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "sales_invoice")
@Data
public class SalesInvoiceImpl implements SalesInvoice {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long uid;

  private String type;
  private String salesOrderCode;
  private BigDecimal amount;
  private BigDecimal auditAmount;
  private String paymentMethod;
  private Date transferFinishDate;
  private String status = 未收款;

  @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @OrderBy("transfer_date")
  private SortedSet<TransferReceipt> receipts;

  @Override
  public void addTransferReceipt(TransferReceipt transferReceipt) {
    receipts.add(transferReceipt);
  }

  @Override
  public void confirmAuditTotal(BigDecimal auditTotal) {
    this.auditAmount = auditTotal;
    this.status = 收款确认;
  }

  @Override
  public BigDecimal getAuditTotal() {
    return this.auditAmount;
  }

  @Override
  public BigDecimal getNotAuditTotal() {
    return this.amount.subtract(this.auditAmount);
  }

  @Override
  public void deleteTransferReceipt(String receiptCode) {
    TransferReceipt receipt = null;
    for (TransferReceipt receipt1 : this.receipts) {
      if (receipt1.getCode().equals(receiptCode)) {
        receipt = receipt1;
      }
    }
    this.receipts.remove(receipt);
  }
}
