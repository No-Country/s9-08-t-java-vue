package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;

public enum Role {

    USER ("User"),
    ADMIN ("Admin");

    @Column(name = "name")
    private String name;
    Role(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
