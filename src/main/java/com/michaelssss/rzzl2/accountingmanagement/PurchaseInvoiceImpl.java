package com.michaelssss.rzzl2.accountingmanagement;

import lombok.Data;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.SortedSet;

@Entity
@Table(name = "purchase_invoice")
@Data
public class PurchaseInvoiceImpl implements PurchaseInvoice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long uid;

    private String type;
    private String purchaseCode;
    private BigDecimal auditAmount;
    private BigDecimal amount;
    private String paymentMethod;
    private Date transferFinishDate;
    private String status = 未付款;
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
        this.status = 付款确认;
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
