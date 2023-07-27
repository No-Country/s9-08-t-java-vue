package com.nocountry.movenow.dto;

import com.nocountry.movenow.model.enums.Shift;
import com.nocountry.movenow.model.enums.VehicleType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovingDTO {

    String destinationPoint;
    String loadingPoint;
    Boolean insurance;
    Long idUser;
    int crewMembersNumber;
    LocalDate date;
    Shift shift;
    String vehicleType;

}