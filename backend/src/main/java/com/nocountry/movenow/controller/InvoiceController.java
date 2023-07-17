package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Invoice;
import com.nocountry.movenow.service.impl.InvoiceServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/invoice")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;

    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @PostMapping("")
    public ResponseEntity<Invoice> saveInvoice(@RequestBody Invoice invoice) {
        try {
            if (invoice == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Invoice savedInvoice = invoiceService.save(invoice);
            return ResponseEntity.ok(savedInvoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(@PathVariable Long id) {
        try {
            Optional<Invoice> optionalInvoice = invoiceService.findById(id);
            if (optionalInvoice.isPresent()) {
                Invoice invoice = optionalInvoice.get();
                return ResponseEntity.ok(invoice);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAllUsers() {
        try {
            List<Invoice> invoices = invoiceService.getAll();
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable Long id, @RequestBody Invoice invoice) {
        try {
            if (invoiceService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            Invoice updatedInvoice = invoiceService.update(invoice);
            return ResponseEntity.ok(updatedInvoice);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(@PathVariable Long id) {
        try {
            if (invoiceService.findById(id).isEmpty()) {
                return ResponseEntity.notFound().build();
            }
            invoiceService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
