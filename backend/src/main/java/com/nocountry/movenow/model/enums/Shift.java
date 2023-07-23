package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonValue;

public enum Shift {
    First("First", "08:00h - 12:00h"),
    Second("Second", "13:00h - 17:00h");

    private String name;
    private String time;

    Shift(String name, String time) {
        this.name = name;
        this.time = time;
    }

    @JsonValue
    public String getName() {
        return name;
    }

    public String getTime() {
        return time;
    }
}
