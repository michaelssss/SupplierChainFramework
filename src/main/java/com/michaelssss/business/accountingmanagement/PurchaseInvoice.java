package com.michaelssss.business.accountingmanagement;

import java.math.BigDecimal;

public interface PurchaseInvoice extends Invoice {
    void addTransferReceipt(TransferReceipt transferReceipt);

    void confirmAuditTotal(BigDecimal auditTotal);

    BigDecimal getAuditTotal();

    BigDecimal getNotAuditTotal();

    void deleteTransferReceipt(String receiptCode);
}
