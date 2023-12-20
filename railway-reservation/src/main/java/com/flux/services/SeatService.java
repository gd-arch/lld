package com.flux.services;

import com.flux.model.Berth;
import com.flux.model.Coach;
import com.flux.model.Seat;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class SeatService {
    private static SeatService seatService;
    private final int DEFAULT_BIRTH_SIZE = 60;
    private SeatService(){
    }

    public static SeatService getSeatService() {
        if(seatService == null){
            synchronized (SeatService.class){
                if(seatService == null){
                    return new SeatService();
                }
            }
        }
        return seatService;
    }

    public Optional<Seat> allotSeat(@NonNull Coach coach, @NonNull Berth preferredBerth) {
        Optional<Seat> preferredSeat = coach.getSeats()
                .stream()
                .filter(seat -> !seat.isBooked())
                .filter(seat -> seat.getBerth() == preferredBerth)
                .findFirst();

        return preferredSeat.or(() -> coach.getSeats()
                .stream()
                .filter(seat -> !seat.isBooked())
                .findFirst());
    }
    public List<Seat> populateDefaultSeats() {
        List<Seat> seats = new ArrayList<>(DEFAULT_BIRTH_SIZE);
        for (int i = 1; i <= DEFAULT_BIRTH_SIZE; i++) {
            Berth berth = i <= (DEFAULT_BIRTH_SIZE /3) ? Berth.UPPER :
                    i <= 2*(DEFAULT_BIRTH_SIZE /3) ? Berth.MIDDLE : Berth.LOWER;
            Seat seat = new Seat(i, berth);
            seats.add(seat);
        }
        return seats;

    }

}
