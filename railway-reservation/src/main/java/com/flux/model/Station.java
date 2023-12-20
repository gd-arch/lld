package com.flux.model;

import lombok.Getter;
import lombok.NonNull;

@Getter

public class Station {
    @NonNull
    private final String stationCode;
    @NonNull
    private String name;

    public Station(String stationCode, String name) {
        this.stationCode = stationCode;
        this.name = name;
    }

    public void setName(String newName) {
        this.name=newName;
    }
}
