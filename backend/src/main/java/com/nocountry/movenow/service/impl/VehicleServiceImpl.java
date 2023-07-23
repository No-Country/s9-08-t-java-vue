package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.exception.VehicleNotFoundException;
import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.repository.VehicleRepository;
import com.nocountry.movenow.service.VehicleService;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.Optional;

@Service
public class VehicleServiceImpl implements VehicleService {


    private final VehicleRepository vehicleRepository;

    public VehicleServiceImpl(VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
    }


    @Override
    public Vehicle save(Vehicle vehicle) {

        return vehicleRepository.save(vehicle);

    }

    @Override
    public Optional<Vehicle> findById(Long id) {


        if (!vehicleRepository.existsById(id)) {

            throw new RuntimeException("Vehicle not found");
        }
        return vehicleRepository.findById(id);

    }

    @Override
    public boolean delete(Long id) {

        Optional<Vehicle> vehicleOptinal = vehicleRepository.findById(id);
        if (!vehicleOptinal.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        }

        Vehicle vehicle = vehicleOptinal.get();
        vehicle.setSoftDelete(true);
        vehicleRepository.save(vehicle);
        return true;

    }

    @Override
    public Vehicle update(Vehicle vehicle) {

        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicle.getId());
        if (!vehicleOptional.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        }

        Vehicle vehicleUpdated = vehicleOptional.get();

        if (vehicle.getDimension() != 0) {
            vehicleUpdated.setDimension(vehicle.getDimension());
        }

        if (vehicle.getWeight() != 0) {
            vehicleUpdated.setWeight(vehicle.getWeight());
        }

        if (vehicle.getModel() != 0) {
            vehicleUpdated.setModel(vehicle.getModel());
        }

        if (vehicle.getType() != null) {
            vehicleUpdated.setType(vehicle.getType());
        }

        if (vehicle.getCapacityDescription() != null) {
            vehicleUpdated.setCapacityDescription(vehicle.getCapacityDescription());
        }

        if (vehicle.getLimitDescription() != null) {
            vehicleUpdated.setLimitDescription(vehicle.getLimitDescription());
        }

        if (vehicle.getImgUrl() != null) {
            vehicleUpdated.setImgUrl(vehicle.getImgUrl());
        }

        if (vehicle.getSchedules() != null) {
            vehicleUpdated.setSchedules(vehicle.getSchedules());
        }

        vehicleUpdated.setStatus(vehicle.getStatus());

        return vehicleRepository.save(vehicleUpdated);


    }

    @Override
    public List<Vehicle> getAll() {

        List<Vehicle> vehicles = vehicleRepository.findAll();
        if (vehicles.isEmpty()) {
            throw new RuntimeException("No vehicles found");
        }

        return vehicles;

    }

    @Override
    public List<Vehicle> getAllByType(String type) {
        List<Vehicle> vehicles = vehicleRepository.getAllByType(type);
        if (vehicles.isEmpty()) {
            throw new RuntimeException("No vehicles found");
        }

        return vehicles;
    }

    @Override
    public Vehicle addImageUrl(String imgUrl, Long id) {

        Optional<Vehicle> vehicleOptinal = vehicleRepository.findById(id);

        if (vehicleOptinal.isEmpty()) {

            throw new VehicleNotFoundException("Vehicle not found");

        }

        Vehicle vehicle = vehicleOptinal.get();

        vehicle.setImgUrl(imgUrl);

        return vehicleRepository.save(vehicle);


    }



}
