package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.Invoice;
import com.nocountry.movenow.repository.InvoiceRepository;
import com.nocountry.movenow.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class InvoiceServiceImpl implements InvoiceService {

    private final InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    @Override
    public Invoice save(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    @Override
    public Optional<Invoice> findById(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

        if (invoiceOptional.isEmpty() || invoiceOptional.get().getSoftDelete()){
            throw new RuntimeException("Invoice not found");
        }

        return invoiceOptional;
    }

    @Override
    public boolean delete(Long id) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(id);

        if (invoiceOptional.isEmpty() || invoiceOptional.get().getSoftDelete()){
            throw new RuntimeException("Invoice not found");
        }

        Invoice invoice = invoiceOptional.get();
        invoice.setSoftDelete(true);
        invoiceRepository.save(invoice);
        return true;
    }

    @Override
    public Invoice update(Invoice invoice) {
        Optional<Invoice> invoiceOptional = invoiceRepository.findById(invoice.getId());

        if (invoiceOptional.isEmpty() || invoiceOptional.get().getSoftDelete()){
            throw new RuntimeException("Invoice not found");
        }

        Invoice invoiceUpdated = invoiceOptional.get();

        if (invoice.getPrice() != null){
            invoiceUpdated.setPrice(invoice.getPrice());
        }

        if (invoice.getStatus() != null){
            invoiceUpdated.setStatus(invoice.getStatus());
        }

        return invoiceRepository.save(invoiceUpdated);
    }

    @Override
    public List<Invoice> getAll() {
        List<Invoice> invoices = invoiceRepository.findAll();
        if (invoices.isEmpty()) {
            throw new RuntimeException("No invoices found");
        }

        return invoices.stream().filter(invoice -> !invoice.getSoftDelete()).collect(Collectors.toList());
    }
}
