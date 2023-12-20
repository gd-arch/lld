package com.flux.api;

import com.flux.model.Coach;
import com.flux.model.User;
import com.flux.services.CoachService;
import lombok.NonNull;

public class CoachController {
    private final CoachService coachService;

    public CoachController(CoachService coachService) {
        this.coachService = coachService;
    }
    public void viewCoachStatus(@NonNull String userId,@NonNull String trainNo, int coachNo){
        try {
            coachService.viewCoachStatus(userId, trainNo, coachNo);
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
        }
    }
}
