package com.nocountry.movenow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jdk.dynalink.linker.LinkerServices;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class CrewMember {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String lastname;

    private String phoneNumber;

    @ManyToMany
    @JoinTable(name = "crew_member_moving",
            joinColumns = @JoinColumn(name = "crew_member_id"),
            inverseJoinColumns = @JoinColumn(name = "moving_id"))
    @JsonIgnore
    private List<Moving> movings;
}
