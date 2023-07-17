package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.exception.DestinationPointNotFoundException;
import com.nocountry.movenow.exception.LoadingPointNotFoundException;
import com.nocountry.movenow.exception.MovingNotFoundException;
import com.nocountry.movenow.exception.UserNotFoundException;
import com.nocountry.movenow.model.*;
import com.nocountry.movenow.repository.CrewMemberRepository;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.repository.UserRepository;
import com.nocountry.movenow.service.MovingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovingServiceImpl implements MovingService {

    private final MovingRepository movingRepository;
    private final CrewMemberRepository crewMemberRepository;

    private final UserRepository userRepository;
    private final SchedulesServiceImpl schedulesServiceImpl;





    MovingServiceImpl(UserRepository userRepository, SchedulesServiceImpl schedulesServiceImpl, MovingRepository movingRepository, CrewMemberRepository crewMemberRepository) {
        this.movingRepository = movingRepository;
        this.crewMemberRepository = crewMemberRepository;
        this.schedulesServiceImpl = schedulesServiceImpl;
        this.userRepository = userRepository;

    }


    @Override
    public Optional<Moving> getMoving(Long movingId ) {
        return movingRepository.findById(movingId);
    }

    @Override
    public Moving save(String destinationPoint, String loadingPoint, Boolean insurance, Long idUser, Long invoiceId, List<CrewMember> crewMembers, Long vehicleId, List<Schedule> schedules) {
        // Check for null destinationPoint and loadingPoint
        if (destinationPoint == null) {
            throw new DestinationPointNotFoundException("Destination point cannot be null");
        }
        if (loadingPoint == null) {
            throw new LoadingPointNotFoundException("Loading point cannot be null");
        }

        // Create a new Moving object
        Moving moving = new Moving();
        moving.setDestinationPoint(destinationPoint);
        moving.setLoadingPoint(loadingPoint);
        moving.setInsurance(insurance);

        // Set the schedules and crew members
        moving.setSchedules(schedules);
        moving.setCrew(crewMembers);

        // Set the user
        UserEntity user = userRepository.findById(idUser).orElseThrow(() -> new UserNotFoundException("User not found"));
        moving.setUser(user);

        // Save the moving object
        moving = movingRepository.save(moving);

        // Save all the schedules
        schedulesServiceImpl.saveAll(schedules, vehicleId, moving.getId());

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
                Optional<CrewMember> crewMemberOptional = crewMemberRepository.findById(crewMemberId);

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
        Optional<CrewMember> crewMemberOptional = crewMemberRepository.findById(crewMemberId);

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
