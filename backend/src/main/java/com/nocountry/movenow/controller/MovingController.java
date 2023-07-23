package com.nocountry.movenow.controller;

import com.nocountry.movenow.dto.MovingDTO;
import com.nocountry.movenow.exception.*;
import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.Schedule;
import com.nocountry.movenow.service.MovingService;
import com.nocountry.movenow.service.impl.MovingServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/moving")
@Tag(name = "Moving", description = "Manage Moving")
public class MovingController {
    private final MovingServiceImpl movingService;

    @Autowired
    public MovingController(MovingServiceImpl movingService) {
        this.movingService = movingService;
    }

    @Operation(summary = "Get a moving by id")
    @GetMapping( path = "/{id}", produces = "application/json" )
    public ResponseEntity<Moving> getMoving(
            @Parameter(description = "Moving id", example = "1")
            @PathVariable Long id){
        return movingService.getMoving(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Operation(summary = "Get all moving")
    @GetMapping( path = "", produces = "application/json" )
    public ResponseEntity<List<Moving>> getAllMoving(){
        return ResponseEntity.ok(movingService.getAllMovings());
    }

    @Operation(summary = "Create a moving")
    @PostMapping
    public ResponseEntity<Moving> saveMoving(
            @Parameter(description = "The data needed to create a moving", example = "MovingDTO")
            @RequestBody MovingDTO movingDTO
    ) {
        try {
            Moving moving = movingService.save(
                    movingDTO
            );
            return ResponseEntity.ok(moving);
        } catch (DestinationPointNotFoundException | LoadingPointNotFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @Operation(summary = "Update a moving")
    @PutMapping("/{id}")
    public ResponseEntity<Moving> updateMoving(
            @Parameter(description = "Update a Moving id", example = "1")
            @RequestBody Moving moving){
        return (moving != null) ?
                (new ResponseEntity<>(movingService.update(moving),HttpStatus.OK))
                : (new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }


    @Operation(summary = "Delete a moving")
    @DeleteMapping("/{id}")
    public ResponseEntity delete(
            @Parameter(description = "Delete a Moving id", example = "1")
            @PathVariable("id") Long movingId){
        return movingService.delete(movingId)?
                ResponseEntity.noContent().build():
                ResponseEntity.notFound().build();
    }


    @Operation(summary = "Add crew members to a moving")
    @PostMapping("/{movingId}/crew")
    public ResponseEntity<Moving> addCrewMembersToMoving(
            @Parameter(description = "Moving id", example = "1")
            @PathVariable Long movingId,
            @Parameter(description = "Crew member ids to add", example = "[1,2]")
            @RequestBody List<Long> crewMemberIds) {
        try {
            Moving updatedMoving = movingService.addCrewMembers(movingId, crewMemberIds);
            return ResponseEntity.ok(updatedMoving);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Operation(summary = "Remove crew members from a moving")
    @PostMapping("/{movingId}/crew/{crewMemberId}")
    public ResponseEntity<Moving> removeCrewMemberFromMoving(
            @Parameter(description = "Moving id", example = "1")
            @PathVariable Long movingId,
            @Parameter(description = "Crew member id to remove", example = "1")
            @PathVariable Long crewMemberId) {
        try {
            if (movingId == null || crewMemberId == null) {
                throw new IllegalArgumentException("Moving id or crew member id is null");
            }

            Moving removedMoving = movingService.removeCrewMember(movingId, crewMemberId);
            return ResponseEntity.ok(removedMoving);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        } catch (MovingNotFoundException | CrewMemberNotFoundException e) {
            return ResponseEntity.notFound().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
