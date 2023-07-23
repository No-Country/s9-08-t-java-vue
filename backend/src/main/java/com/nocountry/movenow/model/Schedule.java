package com.nocountry.movenow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nocountry.movenow.model.enums.Shift;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;
    private LocalDateTime starDate;
    private LocalDateTime endDate;

    @Enumerated(EnumType.STRING)
    private Shift shift;

    @Column(name = "id_vehicle")
    private Long idVehicle;
    @Column(name = "id_moving")
    private Long idMoving;

    @ManyToOne
    @JoinColumn(name = "id_vehicle", insertable = false, updatable = false)
    @JsonIgnore
    private Vehicle vehicle;

    @OneToOne
    @JoinColumn(name = "id_moving", insertable = false, updatable = false)
    @JsonIgnore
    private Moving moving;
}
