package com.nocountry.movenow.service.impl;


import com.nocountry.movenow.exception.MovingNotFoundException;
import com.nocountry.movenow.exception.VehicleNotFoundException;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.Schedule;
import com.nocountry.movenow.model.Vehicle;
import com.nocountry.movenow.model.enums.Shift;
import com.nocountry.movenow.repository.MovingRepository;
import com.nocountry.movenow.repository.ScheduleRepository;
import com.nocountry.movenow.repository.VehicleRepository;
import com.nocountry.movenow.service.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    private final VehicleRepository vehicleRepository;

    private final MovingRepository movingRepository;

    @Autowired
    public ScheduleServiceImpl(MovingRepository movingRepository, ScheduleRepository scheduleRepository, VehicleRepository vehicleRepository) {
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
        if (moving.isEmpty()) {
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

        if (scheduleOptinal.isEmpty()) {
            throw new RuntimeException("Schedule not found");
        }

        Schedule schedule1 = scheduleOptinal.get();

        if (schedule.getStarDateTime() != null) {
            schedule1.setStarDateTime(schedule.getStarDateTime());
        }

        if (schedule.getEndDateTime() != null) {
            schedule1.setEndDateTime(schedule.getEndDateTime());
        }

        if (schedule.getVehicle() != null) {
            schedule1.setVehicle(schedule.getVehicle());
        }

        return scheduleRepository.save(schedule1);

    }

    @Override
    public Schedule buildSchedule(LocalDate date, Shift shift, Long vehicleId) {


        if (vehicleId == null) {
            throw new RuntimeException("Vehicle not found");
        }

        if (date == null) {
            throw new RuntimeException("Start or end dates can't be null.");
        }

        if (shift == null) {
            throw new RuntimeException("Shift can't be null.");
        }

        //Creates Schedule based in selected date and shift
        LocalTime startTime = shift.retrieveStartTime();
        LocalTime endTime = shift.retrieveEndTime();

        Schedule schedule = new Schedule();
        schedule.setStarDateTime(LocalDateTime.of(date, startTime));
        schedule.setEndDateTime(LocalDateTime.of(date, endTime));

        //vehicle assignment to schedule
        Optional<Vehicle> vehicleOptional = vehicleRepository.findById(vehicleId);

        Vehicle vehicle = vehicleOptional
                .orElseThrow(() -> new VehicleNotFoundException("Vehicle not found"));

        schedule.setIdVehicle(vehicleId);
        //schedule.setVehicle(vehicle);

        return schedule;
    }

    @Override
    public List<Shift> getAvailableShifts() {
        List<Shift> availableShifts = new ArrayList<>();

        availableShifts.addAll(Arrays.asList(Shift.values()));

        return availableShifts;
    }

    public int getHoursFromShift(Shift shift) {
        if (shift == null) {
            throw new IllegalArgumentException("Shift cannot be null.");
        }

        return shift.retrieveEndTime().getHour() - shift.retrieveStartTime().getHour();
    }

}
