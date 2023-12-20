package com.flux.services;

import com.flux.exception.CoachAlreadyOccupiedException;
import com.flux.exception.ResourceNotFoundException;
import com.flux.exception.SeatPermanentlyUnavailableException;
import com.flux.model.*;
import com.flux.model.train.Train;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingService {
    private static BookingService bookingService;
    private final CoachService coachService;
    private final SeatService seatService;
    private final UserService userService;
    private final TrainService trainService;
    private final Map<String,List<Booking>> trainBookings;


    private BookingService(CoachService coachService, SeatService seatService, UserService userService, TrainService trainService) {
        this.coachService = coachService;
        this.seatService = seatService;
        this.userService = userService;
        this.trainService = trainService;
        this.trainBookings = new HashMap<>();
    }

    public static BookingService getBookingService(CoachService coachService, SeatService seatService, UserService userService, TrainService trainService) {
        if (bookingService == null) {
            synchronized (BookingService.class) {
                if (bookingService == null) {
                    bookingService = new BookingService(coachService, seatService, userService, trainService);
                }
            }
        }
        return bookingService;
    }

    public Booking createBooking(@NonNull String userId,@NonNull String trainNumber) {
        User user = userService.getUserById(userId).orElseThrow(()->new ResourceNotFoundException(String.format("User with id = %d is not found",userId)));
        Train train = trainService.getTrainByNumber(trainNumber).orElseThrow(()->new ResourceNotFoundException(String.format("Train with no = %d is not found",trainNumber)));
        Coach coach = coachService.allotCoach(train).orElseThrow(()->new CoachAlreadyOccupiedException());
        Seat seat = seatService.allotSeat(coach,user.getPreferredBerth()).orElseThrow(()->new SeatPermanentlyUnavailableException());
        return bookSeat(user, train, coach, seat);
    }

    //synchronised?
    private Booking bookSeat(@NonNull User user,@NonNull Train train,@NonNull Coach coach,@NonNull Seat seat) {
        trainBookings.putIfAbsent(train.getTrainNumber(),new ArrayList<>());
        List<Booking> bookings = trainBookings.get(train.getTrainNumber());
        seat.setBooked(true);
        Booking booking = new Booking(user, train, coach, seat);
        bookings.add(booking);
        System.out.println("Booking successful!");
        return booking;
    }

    public void printBookingDetails(Booking booking){
        System.out.println(booking);
    }

}
