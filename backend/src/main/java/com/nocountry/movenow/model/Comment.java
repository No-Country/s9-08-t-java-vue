package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int stars;

    private String userName;

    private String feedBack;

    @Column(name = "id_moving")
    private Long idMoving;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_moving", insertable = false, updatable = false)
    private Moving moving;
}
