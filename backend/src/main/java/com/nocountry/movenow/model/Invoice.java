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
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private BigDecimal price;

    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_moving", referencedColumnName = "id")
    private Moving moving;
}
