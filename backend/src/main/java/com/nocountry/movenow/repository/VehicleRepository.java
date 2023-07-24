package com.nocountry.movenow.repository;

import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.model.enums.VehicleType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    List<Vehicle> getAllByType(VehicleType type);


}
