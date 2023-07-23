package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.enums.Shift;
import com.nocountry.movenow.service.impl.ScheduleServiceImpl;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api/schedule")
@Tag(name = "Schedules", description = "Manage Schedule properties")
public class ScheduleController {
    private final ScheduleServiceImpl scheduleService;

    @Autowired
    public ScheduleController(ScheduleServiceImpl scheduleService) {
        this.scheduleService = scheduleService;
    }

    @GetMapping("/available-shifts")
    public ResponseEntity<List<Shift>> getAvailableShifts(){
        List<Shift> availableShifts = scheduleService.getAvailableShifts();

        return ResponseEntity.ok(availableShifts);
    }
}
