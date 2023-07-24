package com.nocountry.movenow.model.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.time.LocalDateTime;
import java.time.LocalTime;

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

    public LocalTime retrieveStartTime(){
        if (this.shift == 1){
            return LocalTime.of(8,0);
        } else if (this.shift == 2) {
            return LocalTime.of(13,0);
        } else {
            throw new RuntimeException("The shift selected is incorrect");
        }
    }

    public LocalTime retrieveEndTime(){
        if (this.shift == 1){
            return LocalTime.of(12,0);
        } else if (this.shift == 2) {
            return LocalTime.of(17,0);
        } else {
            throw new RuntimeException("The shift selected is incorrect");
        }
    }
}
