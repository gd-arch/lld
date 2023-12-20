package com.flux.model;

import lombok.Getter;
import lombok.NonNull;

@Getter
public class Seat {
    private final int seatNumber;
    @NonNull
    private final Berth berth;
    private boolean booked;

    public Seat(int seatNumber, Berth berth) {
        this.seatNumber = seatNumber;
        this.berth = berth;
        this.booked = false;
    }

    public void setBooked(boolean booked) {
        this.booked = booked;
    }
}
