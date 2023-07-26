package com.nocountry.movenow.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Data
@Entity
@SQLDelete(sql = "UPDATE billing SET soft_delete = true WHERE id=?")
@Where(clause = "soft_delete = false")
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "billing")
public class BillingStrategy {


    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    @Column(name ="number_helper")
    private int numberOfHelpers=0;
    @Column(name ="hs_quantity")
    private int hsQuantity;
    @Column(name="vehicle_type")
    private String vehicleType;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "id_moving", referencedColumnName = "id")
    private Moving moving;

    @OneToOne(mappedBy = "billingStrategy")
    @JsonIgnore
    private Invoice invoice;

    @Column(name = "final_cost")
    private Double finalCost;


    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;


    public BillingStrategy(int numberOfHelpers, int hsQuantity, String vehicleType, Moving moving) {

        this.numberOfHelpers = numberOfHelpers;
        this.hsQuantity = hsQuantity;
        this.vehicleType = vehicleType;
        this.moving = moving;
    }

    public static BillingStrategy builder(int numberOfHelpers, int hsQuantity, String vehicleType, Moving moving) {

        return new BillingStrategy(numberOfHelpers, hsQuantity, vehicleType, moving);

    }
}
