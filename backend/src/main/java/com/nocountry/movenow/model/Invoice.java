package com.nocountry.movenow.model;


import com.nocountry.movenow.model.enums.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Invoice {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    BigDecimal price;

    @Enumerated(EnumType.STRING)
    Status status;

    Long idMoving;
}
