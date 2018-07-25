package com.jzqh.rzzl2.accountingmanagement;

import java.math.BigDecimal;

public interface ReceiptVoucher extends Voucher {
    void addTransferReceipt(TransferReceipt transferReceipt);

    void confirmAuditTotal(BigDecimal auditTotal);

    BigDecimal getAuditTotal();

    BigDecimal getNotAuditTotal();

    void deleteTransferReceipt(String receiptCode);
}
