package com.nocountry.movenow.repository;

import com.nocountry.movenow.model.Invoice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InvoiceRepository extends JpaRepository<Invoice, Long> {
}
