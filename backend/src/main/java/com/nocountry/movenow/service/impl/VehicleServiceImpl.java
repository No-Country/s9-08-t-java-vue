package com.nocountry.movenow.service.impl;

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

        Optional<Vehicle> vehicleOptinal = vehicleRepository.findById(vehicle.getId());
        if (!vehicleOptinal.isPresent()) {
            throw new RuntimeException("Vehicle not found");
        }

        Vehicle vehicleUpdated = vehicleOptinal.get();

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


}
