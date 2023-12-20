package com.flux.services;

import com.flux.exception.UserAlreadyExistsException;
import com.flux.model.Berth;
import com.flux.model.MealType;
import com.flux.model.User;
import com.flux.model.UserType;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserService {
    private static UserService userService;

    private final List<User> users;

    private UserService() {
        this.users = new ArrayList<>();
    }

    public static UserService getUserService() {
        if (userService == null) {
            synchronized (UserService.class) {
                if (userService == null) {
                    userService = new UserService();
                }
            }
        }
        return userService;
    }
    public boolean isUserAdmin(@NonNull User user) {
      return user.getUserType() == UserType.ADMIN;
    }

    public Optional<User> getUserById(String userId) {
        return users.stream().filter(user -> user.getUserId().equals(userId)).findFirst();
    }

    public String createUser(String name, MealType mealType, Berth preferredBerth, UserType userType) {
        String userId = generateUserId(); // You may implement your own logic to generate userId

        if (getUserById(userId).isPresent()) {
            throw new UserAlreadyExistsException("User with ID " + userId + " already exists.");
        }
        User newUser = new User(userId, name, mealType, preferredBerth, userType);
        users.add(newUser);
        return userId;
    }

    private String generateUserId() {
        return "U" + System.currentTimeMillis();
    }
}
