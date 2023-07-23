package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.service.impl.VehicleServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "Vehicles", description = "Manage Vehicles")
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


    @Operation(description = "Add image url to vehicle")
    @PostMapping("/{id}/image-url")
    public ResponseEntity<Vehicle> addImageUrl(

            @Parameter(description = "Vehicle id", required = true)
            @PathVariable Long id,
            @Parameter(description = "Image url", required = true)
            @RequestBody String imgUrl) {

        if (id == null) {
            return ResponseEntity.badRequest().build();
        }
        if (imgUrl == null) {
            return ResponseEntity.badRequest().build();
        }

        Vehicle updatedVehicle = vehicleService.addImageUrl(imgUrl, id);
        return ResponseEntity.ok(updatedVehicle);
    }

    @Operation(description = "Get all vehicles by type")
    @GetMapping("/{type}")
    public ResponseEntity<List<Vehicle>> getAllVehiclesByType(
            @Parameter(description = "Vehicle type", required = true)
            @PathVariable String type) {

        try {
            List<Vehicle> vehicles = vehicleService.getAllByType(type);
            return ResponseEntity.ok(vehicles);
        } catch (EntityNotFoundException e) {
            return ResponseEntity.notFound().build();
        }

    }



}
