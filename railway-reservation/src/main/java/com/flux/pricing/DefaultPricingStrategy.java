package com.flux.pricing;

public class DefaultPricingStrategy implements PricingStrategy{
    public static final double BASE_PRICE = 2000;
    @Override
    public double calculatePrice(int filledPercentage) {
        return BASE_PRICE;
    }
}
