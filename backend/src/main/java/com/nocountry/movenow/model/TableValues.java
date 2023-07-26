package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.persistence.*;

@Component
@Data
@Table(name = "table_values")
public class TableValues {

    // Singleton instance variable
    private volatile static TableValues instance;

    private Double crewPrice;
    private Double insurance;
    private Double packagingPrice;
    private Double pickUpPrice;
    private Double truckPrice;
    private Double heavyTruckPrice;


    // Private constructor to prevent direct instantiation
    private TableValues() {
        // You can provide default values here or fetch them from a configuration file or database.
        crewPrice = 9.99;
        packagingPrice = 0.0;
        insurance = 19.99;
        pickUpPrice = 15.0;
        truckPrice = 20.0;
        heavyTruckPrice = 25.0;
    }

    // Static method to retrieve the singleton instance
    public static TableValues getInstance() {
        if (instance == null) {
            synchronized (TableValues.class) {
                if (instance == null) {
                    instance = new TableValues();
                }
            }
        }
        return instance;
    }


}
