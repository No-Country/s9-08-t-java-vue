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
@Table(name = "billing")
public class BillingStrategy {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long billingtrategy_id;
    @Column(name ="helper_value")
    private double helperValue;
    @Column(name ="vehicle_value")
    private double vehicleValue;
    @Column(name ="insurance_value")
    private double insuranceValue = 0;
    @Column(name ="number_helper")
    private int numberOfHelpers=0;
    @Column(name ="hs_quantity")
    private int hsQuantity;
    @Column(name ="packaging")
    private double packaging;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_moving", referencedColumnName = "id")
    private Moving moving;

    @OneToOne(mappedBy = "moving")
    @JsonIgnore
    private Invoice invoice;


    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    public BillingStrategy(double helperValue, double vehicleValue, double insuranceValue, int numberOfHelpers,
                           int hsQuantity, double packaging , Moving moving) {
        this.helperValue = helperValue;
        this.vehicleValue = vehicleValue;
        this.insuranceValue = insuranceValue;
        this.numberOfHelpers = numberOfHelpers;
        this.hsQuantity = hsQuantity;
        this.packaging = packaging;
        this.moving = moving;
    }

    public BillingStrategy() {

    }

    public double getHelperValue() {
        return helperValue;
    }

    public void setHelperValue(double helperValue) {
        this.helperValue = helperValue;
    }

    public double getVehicleValue() {
        return vehicleValue;
    }

    public void setVehicleValue(double vehicleValue) {
        this.vehicleValue = vehicleValue;
    }

    public double getInsuranceValue() {
        return insuranceValue;
    }

    public void setInsuranceValue(double insuranceValue) {
        this.insuranceValue = insuranceValue;
    }

    public int getHsQuantity() {
        return hsQuantity;
    }

    public void setChargingHours(int chargingHours) {
        this.hsQuantity = hsQuantity;
    }

    public int getNumberOfHelpers() {
        return numberOfHelpers;
    }

    public void setNumberOfHelpers(int numberOfHelpers) {
        this.numberOfHelpers = numberOfHelpers;
    }

    public double cost(){

        //(vehiclePrice + crewPrice) + (hsQuantity + 2)

    return ((helperValue * numberOfHelpers) + vehicleValue + insuranceValue) * (hsQuantity + 2);

    }

    public double getPackaging() {
        return packaging;
    }

    public void setPackaging(double packaging) {
        this.packaging = packaging;
    }

    public void setBillingtrategy_id(Long billingtrategy_id) {
        this.billingtrategy_id = billingtrategy_id;
    }


    public Long getBillingtrategy_id() {
        return billingtrategy_id;
    }

    public Boolean getSoftDelete() {
        return softDelete;
    }

    public void setSoftDelete(Boolean softDelete) {
        this.softDelete = softDelete;
    }
}
