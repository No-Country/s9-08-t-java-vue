package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Invoice;
import com.nocountry.movenow.service.impl.InvoiceServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/invoice")
@Tag(name = "Invoices", description = "Manage Invoices")
public class InvoiceController {

    private final InvoiceServiceImpl invoiceService;

    @Autowired
    public InvoiceController(InvoiceServiceImpl invoiceService) {
        this.invoiceService = invoiceService;
    }

    @Operation(summary = "Save a new invoice", description = "Save a new invoice")
    @PostMapping("")
    public ResponseEntity<Invoice> saveInvoice(
            @Parameter(description = "Invoice", required = true)
            @RequestBody Invoice invoice) {
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

    @Operation(summary = "Get an invoice by id", description = "Get an invoice by id")
    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoiceById(
            @Parameter(description = "Invoice id", required = true)
            @PathVariable Long id) {
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

    @Operation(summary = "Get all invoices", description = "Get all invoices")
    @GetMapping("")
    public ResponseEntity<List<Invoice>> getAllUsers() {
        try {
            List<Invoice> invoices = invoiceService.getAll();
            return ResponseEntity.ok(invoices);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Update an invoice", description = "Update an invoice")
    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(
            @Parameter(description = "Invoice id", required = true)
            @PathVariable Long id,
            @Parameter(description = "Invoice", required = true)
            @RequestBody Invoice invoice) {
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

    @Operation(summary = "Delete an invoice", description = "Delete an invoice")
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteInvoice(
            @Parameter(description = "Invoice id", required = true)
            @PathVariable Long id) {

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
