package com.nocountry.movenow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@SQLDelete(sql = "UPDATE crew_member SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@Entity
@Table(name = "crew_member")
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

    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;
}
