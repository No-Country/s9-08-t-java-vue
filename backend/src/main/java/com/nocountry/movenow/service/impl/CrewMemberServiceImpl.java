package com.nocountry.movenow.service.impl;

import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.repository.CrewMemberRepository;
import com.nocountry.movenow.service.CrewMemberService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CrewMemberServiceImpl implements CrewMemberService {

    private final CrewMemberRepository crewMemberRepository;

    public CrewMemberServiceImpl(CrewMemberRepository crewMemberRepository) {
        this.crewMemberRepository = crewMemberRepository;
    }

    @Override
    public CrewMember save(CrewMember crewMember) {
        return crewMemberRepository.save(crewMember);
    }

    @Override
    public Optional<CrewMember> findById(Long id) {
        if (!crewMemberRepository.existsById(id)) {
            throw new EntityNotFoundException("Crew member not found with id: " + id);
        }
        return crewMemberRepository.findById(id);
    }

    @Override
    public List<CrewMember> updateAll(List<CrewMember> crewMembers, Moving moving) {


        for (CrewMember crewMember : crewMembers) {
            crewMember.getMovings().add(moving);
            update(crewMember);
        }
        return crewMembers;

    }

    @Override
    public boolean delete(Long id) {
        Optional<CrewMember> crewMemberOptional = crewMemberRepository.findById(id);
        if (!crewMemberOptional.isPresent()) {
            throw new RuntimeException("Crew member not found");
        }

        CrewMember crewMember = crewMemberOptional.get();
        crewMember.setSoftDelete(true);
        crewMemberRepository.save(crewMember);
        return true;
    }

    @Override
    public CrewMember update(CrewMember crewMember) {
        Optional<CrewMember> crewMemberOptional = crewMemberRepository.findById(crewMember.getId());
        if (!crewMemberOptional.isPresent()) {
            throw new RuntimeException("Crew member not found");
        }

        CrewMember crewMemberUpdated = crewMemberOptional.get();

        if (crewMember.getName() != null) {
            crewMemberUpdated.setName(crewMember.getName());
        }

        if (crewMember.getLastname() != null) {
            crewMemberUpdated.setLastname(crewMember.getLastname());
        }

        if (crewMember.getPhoneNumber() != null) {
            crewMemberUpdated.setPhoneNumber(crewMember.getPhoneNumber());
        }

        if (crewMember.getMovings() != null) {
            crewMemberUpdated.setMovings(crewMember.getMovings());
        }

        return crewMemberRepository.save(crewMemberUpdated);
    }

    @Override
    public List<CrewMember> getAll() {
        List<CrewMember> crewMembers = crewMemberRepository.findAll();
        if (crewMembers.isEmpty()) {
            throw new RuntimeException("No crew members found");
        }
        return crewMembers;
    }

    @Override
    public List<CrewMember> findAllById(List<Long> crewMemberIds) {
        List<CrewMember> crewMembers = crewMemberRepository.findAllById(crewMemberIds);
        if (crewMembers.isEmpty()) {
            throw new RuntimeException("No crew members found");
        }
        return crewMembers;
    }

    @Override
    public List<CrewMember> getRandomCrewMembers(int number) {

        List<CrewMember> crewMembers = crewMemberRepository.getCrewMemberByNumber(number);

        if(crewMembers.isEmpty()) {
            throw new RuntimeException("No crew members found");
        }
        return crewMembers;

    }
}