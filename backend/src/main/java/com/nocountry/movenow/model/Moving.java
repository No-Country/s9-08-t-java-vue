package com.nocountry.movenow.model;

import com.nocountry.movenow.model.enums.Package;
import lombok.AllArgsConstructor;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Map;


@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Moving {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    Long id;

    String detinationPoint;

    String loadingPoint;

    @Enumerated(EnumType.STRING)
    Package packageType;

    @OneToMany(mappedBy = "moving")
    Map<Vehicle,Schedule> schedules;

    @ManyToOne
    Invoice invoice;

    @ManyToOne
    Comment comment;

    @OneToMany(mappedBy = "moving")
    List<CrewMember> crew;

    boolean insurance;

    Long idUser;




}
