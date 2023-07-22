package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Schedule;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {


    Schedule save(Schedule schedule, Long movingId);

    Optional<Schedule> findById(Long id);

    void delete(Long id);

    List<Schedule> getAllSchedules();

    Schedule update(Schedule schedule);

    Schedule buildSchedule(LocalDateTime startDates, LocalDateTime endDates, Long vehicleId);
}
