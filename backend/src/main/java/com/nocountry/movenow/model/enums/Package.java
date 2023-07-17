package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;

public enum Package {
    S ("S", "15 m3"),
    M ("M", "25 m3"),
    L ("L", "> 25 m3");

    @Column(name = "name")
    private String name;
    @Column(name = "volume")
    private String volume;

    Package(String name, String volume) {
        this.name = name;
        this.volume = volume;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String getVolume(){
        return volume;
    }

}
