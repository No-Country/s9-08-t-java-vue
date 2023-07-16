package com.nocountry.movenow.service;

import com.nocountry.movenow.model.Moving;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MovingService {
    Optional<Moving> getMoving(Long movingId);
    Moving save(Moving moving);

    Moving update(Moving moving);

    boolean delete(Long movingId);

    public Moving addCrewMembers(Long movingId, List<Long> crewMemberIds);
    Moving removeCrewMember(Long movingId, Long crewMemberId);
}