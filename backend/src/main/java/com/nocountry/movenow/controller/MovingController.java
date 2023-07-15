package com.nocountry.movenow.controller;

import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.service.MovingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/moving")
public class MovingController {
    private final MovingService movingService;

    @Autowired
    public MovingController(MovingService movingService) {
        this.movingService = movingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Moving> getMoving(@PathVariable Long id){
        return movingService.getMoving(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("")
    public ResponseEntity<Moving> saveMoving(@RequestBody Moving moving){
        return (moving != null) ?
                (new ResponseEntity<>(movingService.save(moving),HttpStatus.OK))
                : (new ResponseEntity<>(HttpStatus.BAD_REQUEST));
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
}
