package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {

    List<Schedule> saveAll(List<Schedule> schedules, Long IdVehicle, Long idMoving);

    Optional<Schedule> findById(Long id);

    void delete(Long id);

    List<Schedule> getAllSchedules();

    Schedule update(Schedule schedule);


}
