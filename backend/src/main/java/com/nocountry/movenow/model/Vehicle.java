package com.nocountry.movenow.model;


import com.nocountry.movenow.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private int dimension;

    private double weight;

    private int model;

    private boolean status;

    @OneToMany(mappedBy = "vehicle")
    private List<Schedule> schedules;
}
