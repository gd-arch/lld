package com.flux.pricing;

public class DynamicPricingStrategy implements PricingStrategy{
    public static final double BASE_PRICE = 2000;

    @Override
    public double calculatePrice(int filledPercentage) {
        double currentPrice = BASE_PRICE;
        if (filledPercentage >= 40) {
            currentPrice += 0.18 * BASE_PRICE;
        } else if (filledPercentage >= 35) {
            currentPrice += 0.15 * BASE_PRICE;
        } else if (filledPercentage >= 30) {
            currentPrice += 0.12 * BASE_PRICE;
        } else if (filledPercentage >= 20) {
            currentPrice += 0.10 * BASE_PRICE;
        }
        return currentPrice;
    }
}
