package com.nocountry.movenow.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.movenow.model.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@SQLDelete(sql = "UPDATE vehicle SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private VehicleType type;

    private int dimension;

    private double weight;

    private int model;

    private boolean status;

    @OneToMany(mappedBy = "vehicle", cascade = CascadeType.REMOVE)
    private List<Schedule> schedules;

    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    public void setStatus(boolean status) {
        this.status = status;
    }

    public boolean getStatus() {
        return status;
    }
}
