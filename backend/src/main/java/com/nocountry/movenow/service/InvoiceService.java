package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Invoice;

import java.util.List;
import java.util.Optional;

public interface InvoiceService {
    Invoice save(Invoice invoice);

    Optional<Invoice> findById(Long id);

    boolean delete(Long id);

    Invoice update(Invoice invoice);

    List<Invoice> getAll();
}
