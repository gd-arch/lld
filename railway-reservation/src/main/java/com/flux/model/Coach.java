package com.flux.model;

import com.flux.pricing.DynamicPricingStrategy;
import com.flux.pricing.PricingStrategy;
import lombok.Getter;
import lombok.NonNull;

import java.util.List;
@Getter
public class Coach {
    private final int coachNumber;
    @NonNull
    private final List<Seat> seats;
    private double currentPrice;
    private final PricingStrategy pricingStrategy;
    private boolean isFilled;

    public Coach(int coachNumber,List<Seat> seats) {
        this.coachNumber = coachNumber;
        this.pricingStrategy = new DynamicPricingStrategy();
        this.seats = seats;
        this.isFilled = false;
    }

    public void addSeat(@NonNull Seat seat) {
        if(!isFilled) {
            seats.add(seat);
            updatePrice();
        }
    }
    public void updatePrice() {
        int filledPercentage = calculateFilledPercentage();
        currentPrice = pricingStrategy.calculatePrice(filledPercentage);
    }

    private int calculateFilledPercentage() {
        long bookedSeats = seats.stream().filter(Seat::isBooked).count();
        int filledPercentage = (int) ((bookedSeats * 100) / seats.size());
        if(filledPercentage==100) isFilled = true;
        return filledPercentage;
    }
}
