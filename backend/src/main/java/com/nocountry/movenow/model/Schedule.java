package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;
    LocalDateTime starDate;
    LocalDateTime endDate;
    Long idVehicle;
    Long idMoving;

}
