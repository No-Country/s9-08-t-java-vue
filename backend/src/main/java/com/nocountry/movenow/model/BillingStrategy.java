package com.nocountry.movenow.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
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




    public BillingStrategy(double helperValue, double vehicleValue, double insuranceValue,
                           int numberOfHelpers, int chargingHours, int downloadHours, int travelHours) {
        this.helperValue = helperValue;
        this.vehicleValue = vehicleValue;
        this.insuranceValue = insuranceValue;
        this.numberOfHelpers = numberOfHelpers;
        this.chargingHours = chargingHours;
        this.downloadHours = downloadHours;
        this.travelHours = travelHours;
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

    public void setBillingtrategy_id(Long billingtrategy_id) {
        this.billingtrategy_id = billingtrategy_id;
    }


    public Long getBillingtrategy_id() {
        return billingtrategy_id;
    }
}
