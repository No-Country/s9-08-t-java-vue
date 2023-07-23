package com.nocountry.movenow.model;

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
    @Column(name ="charging_hours")
    private int chargingHours;
    @Column(name ="download_hours")
    private int downloadHours;
    @Column(name ="travel_hours")
    private int travelHours;
    @Column(name ="packaging")
    private double packaging;
    @Column( name="soft_delete")
    private Boolean softDelete  = Boolean.FALSE;

    public BillingStrategy(double helperValue, double vehicleValue, double insuranceValue, int numberOfHelpers,
                           int chargingHours, int downloadHours, int travelHours, double packaging) {
        this.helperValue = helperValue;
        this.vehicleValue = vehicleValue;
        this.insuranceValue = insuranceValue;
        this.numberOfHelpers = numberOfHelpers;
        this.chargingHours = chargingHours;
        this.downloadHours = downloadHours;
        this.travelHours = travelHours;
        this.packaging = packaging;
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

    public int getChargingHours() {
        return chargingHours;
    }

    public void setChargingHours(int chargingHours) {
        this.chargingHours = chargingHours;
    }

    public int getDownloadHours() {
        return downloadHours;
    }

    public void setDownloadHours(int downloadHours) {
        this.downloadHours = downloadHours;
    }

    public int getTravelHours() {
        return travelHours;
    }

    public void setTravelHours(int travelHours) {
        this.travelHours = travelHours;
    }

    public int getNumberOfHelpers() {
        return numberOfHelpers;
    }

    public void setNumberOfHelpers(int numberOfHelpers) {
        this.numberOfHelpers = numberOfHelpers;
    }

    public double cost(){
    return ((helperValue * numberOfHelpers) + vehicleValue + insuranceValue) * (chargingHours + downloadHours + travelHours);

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
