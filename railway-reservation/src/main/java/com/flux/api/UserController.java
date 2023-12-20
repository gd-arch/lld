package com.flux.api;

import com.flux.model.*;
import com.flux.services.UserService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class UserController {
    private final UserService userService;

    public String createUser(@NonNull String name,@NonNull MealType mealType,@NonNull Berth preferredBerth,@NonNull UserType userType) {
        try {
            String userId = userService.createUser(name, mealType, preferredBerth, userType);
            System.out.println("User created successfully.");
            return userId;
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
            return null;
        }
    }



}