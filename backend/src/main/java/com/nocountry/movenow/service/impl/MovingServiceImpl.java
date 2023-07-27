package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.dto.MovingDTO;
import com.nocountry.movenow.exception.*;
import com.nocountry.movenow.model.*;
import com.nocountry.movenow.repository.BillingStrategyRepository;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.service.MovingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovingServiceImpl implements MovingService {

    private final MovingRepository movingRepository;
    private final CrewMemberServiceImpl crewMemberService;
    private final UserServiceImpl userService;
    private final ScheduleServiceImpl scheduleServiceImpl;
    private final VehicleServiceImpl vehicleService;
    private final BillingStrategyServiceImpl billingStrategyService;

    MovingServiceImpl( BillingStrategyServiceImpl billingStrategyService , UserServiceImpl userRepository, ScheduleServiceImpl scheduleServiceImpl, MovingRepository movingRepository, CrewMemberServiceImpl crewMemberService, VehicleServiceImpl vehicleService) {
        this.movingRepository = movingRepository;
        this.crewMemberService = crewMemberService;
        this.scheduleServiceImpl = scheduleServiceImpl;
        this.userService = userRepository;
        this.vehicleService = vehicleService;
        this.billingStrategyService = billingStrategyService;
    }


    @Override
    public Optional<Moving> getMoving(Long movingId ) {
        return movingRepository.findById(movingId);
    }

    @Override
    public Moving save(MovingDTO movingDTO) {

        // Check for null destinationPoint and loadingPoint
        if (movingDTO.getDestinationPoint() == null) {
            throw new DestinationPointNotFoundException("Destination point cannot be null");
        }
        if (movingDTO.getLoadingPoint() == null) {
            throw new LoadingPointNotFoundException("Loading point cannot be null");
        }

        // Create a new Moving object
        Moving moving = new Moving();
        moving.setDestinationPoint(movingDTO.getDestinationPoint());
        moving.setLoadingPoint(movingDTO.getLoadingPoint());
        moving.setInsurance(movingDTO.getInsurance());

        //Get random vehicle by selected type
        List<Vehicle> vehiclesByType = vehicleService.getAllByType(movingDTO.getVehicleType());

        Vehicle randomVehicle = vehiclesByType.stream()
                .filter(Vehicle::getStatus)
                .findAny()
                .orElseThrow(() -> new VehicleNotFoundException("Neither vehicle of the type " + movingDTO.getVehicleType() + " available."));

        randomVehicle.setStatus(false);

        // Create a list of schedules with the provided start and end dates, vehicleId and moving
        Schedule schedule = scheduleServiceImpl.buildSchedule(movingDTO.getDate(), movingDTO.getShift(), randomVehicle.getId());

        // Retrieve a quantity of CrewMembers from repository

        List<CrewMember> crewMembers = crewMemberService.getRandomCrewMembers(movingDTO.getCrewMembersNumber());

        // Ensure that all provided CrewMembers exist in the repository
        if (crewMembers.size() != movingDTO.getCrewMembersNumber()) {
            throw new CrewMemberNotFoundException("One or more CrewMembers not found");
        }

        // Set the crew members to the moving
        moving.setCrew(crewMembers);

        //Verify that the user exists
        UserEntity user = userService.findById(movingDTO.getIdUser())
                .orElseThrow(() -> new RuntimeException("Not user found with that id."));

        moving.setIdUser(user.getId());

        // Set the user
        moving.setUser(user);

        // Extract hours from the shift

        int hsQuantity = scheduleServiceImpl.getHoursFromShift(movingDTO.getShift());

        // Create the BillingStrategy for the moving
        BillingStrategy billingStrategy = billingStrategyService.save(movingDTO.getCrewMembersNumber(), hsQuantity, movingDTO.getVehicleType(), movingDTO.getInsurance());


        return movingRepository.save(moving);
    }

    @Override
    public Moving update(Moving moving) {
        Long movingId = moving.getId();
        Optional<Moving> existingMovingOptional = movingRepository.findById(movingId);

        if (existingMovingOptional.isPresent()) {
            Moving existingMoving = existingMovingOptional.get();
            // Update the attributes if they are not null
            existingMoving.setDestinationPoint(moving.getDestinationPoint() != null ? moving.getDestinationPoint() : existingMoving.getDestinationPoint());
            existingMoving.setLoadingPoint(moving.getLoadingPoint() != null ? moving.getLoadingPoint() : existingMoving.getLoadingPoint());
            existingMoving.setInsurance(moving.isInsurance());
            existingMoving.setSchedules(moving.getSchedules() != null ? moving.getSchedules() : existingMoving.getSchedules());
            existingMoving.setCrew(moving.getCrew() != null ? moving.getCrew() : existingMoving.getCrew());

            return movingRepository.save(existingMoving);
        } else {
            throw new RuntimeException("Moving not found");
        }
    }

    @Override
    public boolean delete(Long movingId) {
        Optional<Moving> movingOptinal = movingRepository.findById(movingId);

        if (movingOptinal.isPresent()) {
            Moving moving = movingOptinal.get();
            moving.setSoftDelete(true);
            movingRepository.save(moving);
            return true;
        } else {
            return false;
        }

    }

    public List<Moving> getAllMovings() {

        List<Moving> movings = movingRepository.findAll();

        if (movings.isEmpty()) {
            throw new RuntimeException("No hay movings");
        }

        return movings;

    }



    @Override
    public Moving addCrewMembers(Long movingId, List<Long> crewMemberIds) {
        Optional<Moving> movingOptional = movingRepository.findById(movingId);

        if (movingOptional.isPresent()) {
            Moving moving = movingOptional.get();
            List<CrewMember> crew = moving.getCrew();

            for (Long crewMemberId : crewMemberIds) {
                Optional<CrewMember> crewMemberOptional = crewMemberService.findById(crewMemberId);

                if (crewMemberOptional.isPresent()) {
                    CrewMember crewMember = crewMemberOptional.get();
                    crew.add(crewMember);
                } else {
                    throw new RuntimeException("CrewMember with ID " + crewMemberId + " not found");
                }
            }

            moving.setCrew(crew);
            return movingRepository.save(moving);
        } else {
            throw new RuntimeException("Moving not found");
        }
    }

    @Override
    public Moving removeCrewMember(Long movingId, Long crewMemberId) {
        Optional<Moving> movingOptional = movingRepository.findById(movingId);
        Optional<CrewMember> crewMemberOptional = crewMemberService.findById(crewMemberId);

        if (movingOptional.isPresent() && crewMemberOptional.isPresent()) {
            Moving moving = movingOptional.get();
            CrewMember crewMember = crewMemberOptional.get();

            List<CrewMember> crew = moving.getCrew();
            crew.remove(crewMember);
            moving.setCrew(crew);

            return movingRepository.save(moving);
        } else {
            throw new MovingNotFoundException("Moving or CrewMember not found");
        }
    }

    public Moving findById(Long idMoving) {
        Optional<Moving> movingOptional = movingRepository.findById(idMoving);
        if (movingOptional.isPresent()) {
            return movingOptional.get();
        }
        throw new MovingNotFoundException("Moving not found");
    }


}
