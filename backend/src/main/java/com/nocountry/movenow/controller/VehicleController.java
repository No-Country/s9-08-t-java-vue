package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.service.impl.VehicleServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/vehicle")
public class VehicleController {

    private final VehicleServiceImpl vehicleService;

    @Autowired
    public VehicleController(VehicleServiceImpl vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping("")
    public ResponseEntity<Vehicle> saveVehicle(@RequestBody Vehicle vehicle) {
        try {
            if (vehicle == null) {
                return ResponseEntity.badRequest().body(null);
            }

            Vehicle savedVehicle = vehicleService.save(vehicle);
            return ResponseEntity.ok(savedVehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Vehicle> getVehicleById(@PathVariable Long id) {
        try {
            Optional<Vehicle> optionalVehicle = vehicleService.findById(id);
            if (optionalVehicle.isPresent()) {
                Vehicle vehicle = optionalVehicle.get();
                return ResponseEntity.ok(vehicle);
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<Vehicle>> getAllVehicles() {
        try {
            List<Vehicle> vehicles = vehicleService.getAll();
            return ResponseEntity.ok(vehicles);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Vehicle> updateVehicle(@PathVariable Long id, @RequestBody Vehicle vehicle) {
        try {
            if (!vehicleService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            Vehicle updatedVehicle = vehicleService.update(vehicle);
            return ResponseEntity.ok(updatedVehicle);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id) {
        try {
            if (!vehicleService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            vehicleService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
