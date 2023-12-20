package com.flux.api;

import com.flux.model.Booking;
import com.flux.model.User;
import com.flux.model.train.Train;
import com.flux.services.BookingService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class BookingController {
    private final BookingService bookingService;

    public Booking createBooking(@NonNull String userId,@NonNull String trainNumber) {
        try{
            return bookingService.createBooking(userId,trainNumber);
        }catch (Exception exception){
            System.out.println("Exception occured:"+exception.getMessage());
            return null;
        }
    }
    public void printBookingDetails(Booking booking){
        try{
            bookingService.printBookingDetails(booking);
        }catch (Exception exception){
            System.out.println("Exception occured:"+exception.getMessage());
        }
    }
}
