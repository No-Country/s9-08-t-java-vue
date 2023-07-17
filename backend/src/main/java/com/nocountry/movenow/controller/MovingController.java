package com.nocountry.movenow.controller;

import com.nocountry.movenow.exception.*;
import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.Schedule;
import com.nocountry.movenow.service.MovingService;
import com.nocountry.movenow.service.impl.MovingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/moving")
public class MovingController {
    private final MovingServiceImpl movingService;

    @Autowired
    public MovingController(MovingServiceImpl movingService) {
        this.movingService = movingService;
    }

    @GetMapping( path = "/{id}", produces = "application/json" )
    public ResponseEntity<Moving> getMoving(@PathVariable Long id){
        return movingService.getMoving(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @GetMapping( path = "", produces = "application/json" )
    public ResponseEntity<List<Moving>> getAllMoving(){
        return ResponseEntity.ok(movingService.getAllMovings());
    }

    @PostMapping
    public ResponseEntity<Moving> saveMoving(
            @RequestParam String destinationPoint,
            @RequestParam String loadingPoint,
            @RequestParam Boolean insurance,
            @RequestParam Long idUser,
            @RequestParam Long invoiceId,
            @RequestBody List<CrewMember> crewMembers,
            @RequestParam Long vehicleId,
            @RequestBody List<Schedule> schedules) {
        try {
            Moving moving = movingService.save(
                    destinationPoint,
                    loadingPoint,
                    insurance,
                    idUser,
                    invoiceId,
                    crewMembers,
                    vehicleId,
                    schedules
            );
            return ResponseEntity.ok(moving);
        } catch (DestinationPointNotFoundException | LoadingPointNotFoundException | UserNotFoundException e) {
            return ResponseEntity.badRequest().body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Moving> updateMoving(@RequestBody Moving moving){
        return (moving != null) ?
                (new ResponseEntity<>(movingService.update(moving),HttpStatus.OK))
                : (new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable("id") Long movingId){
        return movingService.delete(movingId)?
                ResponseEntity.noContent().build():
                ResponseEntity.notFound().build();
    }

    @PostMapping("/{movingId}/crew")
    public ResponseEntity<Moving> addCrewMembersToMoving(@PathVariable Long movingId, @RequestBody List<Long> crewMemberIds) {
        try {
            Moving updatedMoving = movingService.addCrewMembers(movingId, crewMemberIds);
            return ResponseEntity.ok(updatedMoving);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/{movingId}/crew/{crewMemberId}")
    public ResponseEntity<Moving> removeCrewMemberFromMoving(@PathVariable Long movingId, @PathVariable Long crewMemberId) {
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
