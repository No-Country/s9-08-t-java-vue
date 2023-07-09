package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;

public enum VehicleType {
    PICK_UP ("Pick up"),
    TRUCK ("Truck"),
    HEAVY_TRUCK ("Heavy truck");

    @Column(name = "name")
    private String name;

    VehicleType(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
