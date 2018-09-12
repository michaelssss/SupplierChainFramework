package com.michaelssss.business.accountingmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseInvoiceCatalog extends JpaRepository<PurchaseInvoiceImpl, Long> {
}
