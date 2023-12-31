package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Schedule;
import com.nocountry.movenow.model.enums.Shift;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ScheduleService {


    Schedule save(Schedule schedule, Long movingId);

    Optional<Schedule> findById(Long id);

    void delete(Long id);

    List<Schedule> getAllSchedules();

    Schedule update(Schedule schedule);

    Schedule buildSchedule(LocalDate date, Shift shift, Long vehicleId);

    List<Shift> getAvailableShifts();
}
