package com.michaelssss.business.accountingmanagement;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SalesInvoiceCatalog extends JpaRepository<SalesInvoiceImpl, Long> {
}
