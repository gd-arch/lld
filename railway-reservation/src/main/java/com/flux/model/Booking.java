package com.flux.model;

import com.flux.exception.InvalidStateException;
import com.flux.model.train.Train;
import lombok.*;

import java.time.LocalDate;

@Getter
public class Booking {
    @Override
    public String toString() {
        return "Booking{" +
                "user=" + user +
                ", train=" + train.getTrainNumber() +
                ", coach=" + coach.getCoachNumber() +
                ", seat=" + seat.getSeatNumber() +
                ", bookingStatus=" + bookingStatus +
                ", bookingDate=" + bookingDate +
                '}';
    }

    @NonNull
    private final User user;
    @NonNull
    private final Train train;
    @NonNull
    private final Coach coach;
    @NonNull
    private final Seat seat;
    @NonNull
    private BookingStatus bookingStatus;
    @NonNull
    private LocalDate bookingDate;

    public Booking(@NonNull User user, @NonNull Train train, @NonNull Coach coach, @NonNull Seat seat) {
        this.user = user;
        this.train = train;
        this.coach = coach;
        this.seat = seat;
        this.bookingStatus = BookingStatus.CREATED;
        this.bookingDate = LocalDate.now();
    }

    public boolean isConfirmed() {
        return this.bookingStatus == BookingStatus.CONFIRMED;
    }

    public void confirmBooking() {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.CONFIRMED;
    }

    public void expireBooking() {
        if (this.bookingStatus != BookingStatus.CREATED) {
            throw new InvalidStateException();
        }
        this.bookingStatus = BookingStatus.EXPIRED;
    }

}
