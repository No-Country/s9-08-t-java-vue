package com.nocountry.movenow.controller;

import com.lowagie.text.DocumentException;
import com.nocountry.movenow.service.PdfService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/api/pdf-generate")
public class PdfGenerateController {

    private final PdfService pdfService;

    @Autowired
    public PdfGenerateController(PdfService pdfService) {
        this.pdfService = pdfService;
    }

    @GetMapping("/{invoiceId}")
    public void downloadPDFResource(@PathVariable Long invoiceId, HttpServletResponse response) {
        try {
            Path file = Paths.get(pdfService.generatePdf(invoiceId).getAbsolutePath());
            if (Files.exists(file)) {
                response.setContentType("application/pdf");
                response.addHeader("Content-Disposition",
                        "attachment; filename=factura_" + invoiceId + ".pdf");
                Files.copy(file, response.getOutputStream());
                response.getOutputStream().flush();
            }
        } catch (DocumentException | IOException ex) {
            ex.printStackTrace();
        }
    }
}