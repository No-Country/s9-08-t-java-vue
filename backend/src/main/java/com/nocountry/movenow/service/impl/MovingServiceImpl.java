package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.dto.MovingDTO;
import com.nocountry.movenow.exception.*;
import com.nocountry.movenow.model.*;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.repository.UserRepository;
import com.nocountry.movenow.service.MovingService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovingServiceImpl implements MovingService {

    private final MovingRepository movingRepository;
    private final CrewMemberServiceImpl crewMemberService;
    private final UserRepository userRepository;
    private final SchedulesServiceImpl schedulesServiceImpl;





    MovingServiceImpl(UserRepository userRepository, SchedulesServiceImpl schedulesServiceImpl, MovingRepository movingRepository, CrewMemberServiceImpl crewMemberService) {
        this.movingRepository = movingRepository;
        this.crewMemberService = crewMemberService;
        this.schedulesServiceImpl = schedulesServiceImpl;
        this.userRepository = userRepository;

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
        moving.setInsurance(moving.isInsurance());

        // Create a list of schedules with the provided start and end dates, vehicleId and moving
        Schedule schedule = schedulesServiceImpl.buildSchedule(movingDTO.getStart(), movingDTO.getEnds(), movingDTO.getVehicleId());

        // Retrieve CrewMembers from repository using the provided IDs
        List<CrewMember> crewMembers = crewMemberService.findAllById(movingDTO.getCrewMembersIds());
        // Ensure that all provided CrewMembers exist in the repository
        if (crewMembers.size() != movingDTO.getCrewMembersIds().size()) {
            throw new CrewMemberNotFoundException("One or more CrewMembers not found");
        }

        // Set the crew members to the moving
        moving.setCrew(crewMembers);


        //Verify that the user exists

        System.out.println(userRepository.findById(movingDTO.getIdUser()).get().toString());
        UserEntity user = userRepository.findById(movingDTO.getIdUser()).get();




        // Set the user
        moving.setUser(user);

        // Save the moving object
        moving = movingRepository.save(moving);

        // Save all the schedules
        schedulesServiceImpl.save(schedule, moving.getId());
        //Update crew members asigning the moving
        crewMemberService.updateAll(crewMembers,moving);

        return moving;
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
