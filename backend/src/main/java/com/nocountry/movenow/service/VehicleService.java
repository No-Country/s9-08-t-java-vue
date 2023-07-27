package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {

    Vehicle save(Vehicle vehicle);

    Optional<Vehicle> findById(Long id);

    boolean delete(Long id);

    Vehicle update(Vehicle vehicle);

    List<Vehicle> getAll();


    List<Vehicle> getAllByType(String type);

    Vehicle addImageUrl(String imgUrl, Long id);
}
