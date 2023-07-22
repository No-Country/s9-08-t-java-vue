package com.nocountry.movenow.service.impl;


import com.nocountry.movenow.exception.MovingNotFoundException;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.Schedule;
import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.repository.ScheduleRepository;
import com.nocountry.movenow.repository.VehicleRepository;
import com.nocountry.movenow.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class SchedulesServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final VehicleRepository vehicleRepository;

    private MovingRepository movingRepository;

    @Autowired
    public SchedulesServiceImpl(MovingRepository movingRepository, ScheduleRepository scheduleRepository, VehicleRepository vehicleRepository) {
        this.vehicleRepository = vehicleRepository;
        this.scheduleRepository = scheduleRepository;
        this.movingRepository = movingRepository;

    }

    @Override
    public Schedule save(Schedule schedule, Long movingId) {

        if (schedule == null) {
            throw new RuntimeException("Schedule not found");
        }

        Optional<Moving> moving = movingRepository.findById(movingId);
        if (moving == null) {
            throw new MovingNotFoundException("Moving not found");
        }

        schedule.setMoving(moving.get());
        schedule.setIdMoving(movingId);


        return scheduleRepository.save(schedule);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return scheduleRepository.findById(id);
    }

    @Override
    public void delete(Long id) {
        scheduleRepository.deleteById(id);
    }

    @Override
    public List<Schedule> getAllSchedules() {
        return scheduleRepository.findAll();
    }

    @Override
    public Schedule update(Schedule schedule) {

        Optional<Schedule> scheduleOptinal = scheduleRepository.findById(schedule.getId());

        if (!scheduleOptinal.isPresent()) {
            throw new RuntimeException("Schedule not found");
        }

        Schedule schedule1 = scheduleOptinal.get();

        if (schedule.getStarDate() != null) {
            schedule1.setStarDate(schedule.getStarDate());
        }

        if (schedule.getEndDate() != null) {
            schedule1.setEndDate(schedule.getEndDate());
        }

        if (schedule.getVehicle() != null) {
            schedule1.setVehicle(schedule.getVehicle());
        }

        return scheduleRepository.save(schedule1);

    }

    @Override
    public Schedule buildSchedule(LocalDateTime startDates, LocalDateTime endDates, Long vehicleId) {


        if (vehicleId == null) {
            throw new RuntimeException("Vehicle not found");
        }

        if (startDates == null || endDates == null) {
            throw new RuntimeException("Dates not match");
        }

       Schedule schedule = new Schedule();
       schedule.setStarDate(startDates);
       schedule.setEndDate(endDates);

        Vehicle vehicle = vehicleRepository.findById(vehicleId).get();

       schedule.setIdVehicle(vehicleId);
       schedule.setVehicle(vehicle);

        return schedule;
    }

}
