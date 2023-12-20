package com.flux.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class User {
    @NonNull
    private final String userId;
    @NonNull
    private final String name;
    @NonNull
    private final MealType mealType;
    @NonNull
    private final Berth preferredBerth;
    @NonNull
    private final UserType userType;


}
