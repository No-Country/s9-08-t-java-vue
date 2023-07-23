package com.nocountry.movenow.dto;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class MovingDTO {

    String destinationPoint;
    String loadingPoint;
    Boolean insurance;
    Long idUser;
    List<Long> crewMembersIds;
    LocalDateTime start;
    LocalDateTime ends;
    Long vehicleId;

}