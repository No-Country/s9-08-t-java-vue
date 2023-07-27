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
@Entity
@SQLDelete(sql = "UPDATE moving SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Moving {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private String destinationPoint;

    private String loadingPoint;

    private boolean insurance;

    @Column(name = "id_user")
    private Long idUser;

    @ManyToOne
    @JoinColumn(name = "id_user", insertable = false, updatable = false)
    @JsonIgnore
    private UserEntity user;

    /*OneToOne(mappedBy = "moving")
    @JsonIgnore
    private Invoice invoice;*/


    @OneToOne(mappedBy = "moving", cascade = CascadeType.PERSIST , orphanRemoval = true)
    @JsonIgnore
    private BillingStrategy billingStrategy;

    @OneToOne(mappedBy = "moving")
    @JsonIgnore
    private Comment comment;

    @OneToOne(mappedBy = "moving", cascade = CascadeType.PERSIST , orphanRemoval = true)
    @JsonIgnore
    private Schedule schedules;

    @ManyToMany(mappedBy = "movings", cascade = CascadeType.PERSIST)
    private List<CrewMember> crew;


    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;
}
