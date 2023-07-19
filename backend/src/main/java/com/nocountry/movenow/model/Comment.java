package com.nocountry.movenow.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@SQLDelete(sql = "UPDATE comment SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private int stars;

    private String userName;

    private String feedBack;

    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    @Column(name = "id_moving")
    private Long idMoving;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_moving", insertable = false, updatable = false)
    private Moving moving;
}
