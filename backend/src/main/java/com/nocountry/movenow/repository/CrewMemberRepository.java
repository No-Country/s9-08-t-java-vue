package com.nocountry.movenow.repository;

import com.nocountry.movenow.model.CrewMember;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CrewMemberRepository extends JpaRepository<CrewMember, Long> {

    @Query(value = "SELECT * FROM crew_member ORDER BY RAND() LIMIT :number", nativeQuery = true)
    List<CrewMember> getCrewMemberByNumber(int number);


}
