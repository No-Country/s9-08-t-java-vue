package com.nocountry.movenow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    private Long id;

    private String destinationPoint;

    private String loadingPoint;

    @Enumerated(EnumType.STRING)
    private Package packageType;

    private boolean insurance;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    @OneToOne(mappedBy = "moving")
    @JsonIgnore
    private Invoice invoice;

    @OneToOne(mappedBy = "moving")
    @JsonIgnore
    private Comment comment;

    @OneToMany(mappedBy = "moving")
    private List<Schedule> schedules;

    @ManyToMany(mappedBy = "movings")
    private List<CrewMember> crew;


}
