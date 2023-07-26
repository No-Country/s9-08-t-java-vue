package com.nocountry.movenow.service;

import com.lowagie.text.DocumentException;
import com.nocountry.movenow.model.Invoice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.xhtmlrenderer.pdf.ITextRenderer;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

@Service
public class PdfService {

    private static final String PDF_RESOURCES = "/pdf-resources/";
    private InvoiceService invoiceService;
    private SpringTemplateEngine templateEngine;

    @Autowired
    public PdfService(InvoiceService invoiceService, SpringTemplateEngine templateEngine) {
        this.invoiceService = invoiceService;
        this.templateEngine = templateEngine;
    }

    public File generatePdf(Long invoiceId) throws IOException, DocumentException {
        Context context = getContext(invoiceId);
        String html = loadAndFillTemplate(context);
        return renderPdf(html);
    }

    private File renderPdf(String html) throws IOException, DocumentException {
        File file = File.createTempFile("factura", ".pdf");
        OutputStream outputStream = new FileOutputStream(file);
        ITextRenderer renderer = new ITextRenderer(20f * 4f / 3f, 20);
        renderer.setDocumentFromString(html, new ClassPathResource(PDF_RESOURCES).getURL().toExternalForm());
        renderer.layout();
        renderer.createPDF(outputStream);
        outputStream.close();
        file.deleteOnExit();
        return file;
    }

    private Context getContext(Long invoiceId) {
        Context context = new Context();
        Invoice invoice = invoiceService.findById(invoiceId).orElse(null);
        if (invoice != null) {
            context.setVariable("invoice", invoice);
            context.setVariable("moving", invoice.getMoving());
            context.setVariable("user", invoice.getMoving().getUser());
        }
        return context;
    }

    private String loadAndFillTemplate(Context context) {
        return templateEngine.process("invoice_template", context);
    }
}