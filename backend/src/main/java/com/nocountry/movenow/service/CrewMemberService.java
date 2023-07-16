package com.nocountry.movenow.service;

import com.nocountry.movenow.model.CrewMember;

import java.util.List;
import java.util.Optional;

public interface CrewMemberService {

    CrewMember save(CrewMember crewMember);

    Optional<CrewMember> findById(Long id);

    boolean delete(Long id);

    CrewMember update(CrewMember crewMember);

    List<CrewMember> getAll();

}
