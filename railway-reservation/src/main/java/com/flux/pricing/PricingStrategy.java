package com.flux.pricing;

public interface PricingStrategy {
    double calculatePrice(int filledPercentage);
}
