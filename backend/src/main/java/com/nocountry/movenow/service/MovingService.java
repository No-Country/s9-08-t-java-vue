package com.nocountry.movenow.service;

import com.nocountry.movenow.model.CrewMember;
import com.nocountry.movenow.model.Moving;
import com.nocountry.movenow.model.Schedule;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


public interface MovingService {
    Optional<Moving> getMoving(Long movingId);

    public Moving findById(Long idMoving);
    public Moving save(String destinationPoint, String loadingPoint, Boolean insurance, Long idUser, Long invoiceId, List<CrewMember> crewMembers, Long vehicleId, List<Schedule> schedules);

    Moving update(Moving moving);

    boolean delete(Long movingId);

    public Moving addCrewMembers(Long movingId, List<Long> crewMemberIds);
    Moving removeCrewMember(Long movingId, Long crewMemberId);
}
