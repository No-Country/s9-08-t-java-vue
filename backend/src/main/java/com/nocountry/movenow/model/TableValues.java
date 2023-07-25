package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "table_values")
public class TableValues {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    Double crewPrice;

    Double insurance;

    Double vehiclePrice;

    Double hsPrice ;


    public TableValues(Double crewPrice, Double insurance, Double vehiclePrice, Double hsPrice) {

        this.crewPrice = crewPrice;
        this.insurance = insurance;
        this.vehiclePrice = vehiclePrice;
        this.hsPrice = hsPrice;
    }


}
