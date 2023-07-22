package com.nocountry.movenow.service;

import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.model.Moving;

import java.util.List;
import java.util.Optional;

public interface CrewMemberService {

    CrewMember save(CrewMember crewMember);

    Optional<CrewMember> findById(Long id);

    List<CrewMember> updateAll(List<CrewMember> crewMembers, Moving moving);

    boolean delete(Long id);

    CrewMember update(CrewMember crewMember);

    List<CrewMember> getAll();

    List<CrewMember> findAllById(List<Long> crewMemberIds);
}
