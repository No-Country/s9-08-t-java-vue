package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Shift {
    First(1, "Primer turno: 08:00h - 12:00h"),
    Second(2, "Segundo turno: 13:00h - 17:00h");

    private final int shift;
    private final String time;

    Shift(int shift, String time) {
        this.shift = shift;
        this.time = time;
    }

    @JsonCreator
    public int getShift() {
        return shift;
    }

    @JsonValue
    public String getTime() {
        return time;
    }
}
