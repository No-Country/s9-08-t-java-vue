package com.nocountry.movenow.controller;


import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.service.impl.CrewMemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/crew-members")
public class CrewMemberController {

    private final CrewMemberServiceImpl crewMemberService;

    @Autowired
    public CrewMemberController(CrewMemberServiceImpl crewMemberService) {
        this.crewMemberService = crewMemberService;
    }

    @PostMapping("")
    public ResponseEntity<CrewMember> saveCrewMember(@RequestBody CrewMember crewMember) {
        try {
            if (crewMember == null) {
                return ResponseEntity.badRequest().body(null);
            }

            CrewMember savedCrewMember = crewMemberService.save(crewMember);
            return ResponseEntity.ok(savedCrewMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CrewMember> getCrewMemberById(@PathVariable Long id) {
        try {
            Optional<CrewMember> optionalCrewMember = crewMemberService.findById(id);
            return ResponseEntity.ok(optionalCrewMember.orElse(null));
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @GetMapping("/")
    public ResponseEntity<List<CrewMember>> getAllCrewMembers() {
        try {
            List<CrewMember> crewMembers = crewMemberService.getAll();
            return ResponseEntity.ok(crewMembers);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CrewMember> updateCrewMember(@PathVariable Long id, @RequestBody CrewMember crewMember) {
        try {
            if (!crewMemberService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            CrewMember updatedCrewMember = crewMemberService.update(crewMember);
            return ResponseEntity.ok(updatedCrewMember);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCrewMember(@PathVariable Long id) {
        try {
            if (!crewMemberService.findById(id).isPresent()) {
                return ResponseEntity.notFound().build();
            }

            crewMemberService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
