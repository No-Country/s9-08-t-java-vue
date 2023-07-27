package com.nocountry.movenow.model;


import com.nocountry.movenow.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

   /* @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_moving", referencedColumnName = "id")
    private Moving moving;*/

    @OneToOne(cascade = CascadeType.PERSIST , orphanRemoval = true)
    @JoinColumn(name="id_billing_strategy", referencedColumnName = "id")
    private BillingStrategy billingStrategy;

    private final String companyName = "Movenow";

    private final String companyAddress = "Av. Bernardo Vasconcelos, n° 377 - CABA";

    private final String companyPhone = "08009792020";

    private final String companyCUIT = "30-123456789-0";

    private static int lastInvoiceNumber = 0;
    private String invoiceNumber;

    public Invoice() {
        lastInvoiceNumber++;
        invoiceNumber = String.format("%08d", lastInvoiceNumber);
    }




}
