package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

import javax.persistence.Column;

public enum Status {
    ACCEPTED ("Accepted"),
    TO_PAY ("To pay"),
    PAYED ("Payed");

    @Column(name = "name")
    private String name;

    Status(String name) {
        this.name = name;
    }

    @JsonValue
    public String getName() {
        return name;
    }
}
