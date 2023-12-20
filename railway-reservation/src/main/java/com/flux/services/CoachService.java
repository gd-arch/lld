package com.flux.services;

import com.flux.dto.CoachStatus;
import com.flux.exception.NotAllowedException;
import com.flux.exception.ResourceNotFoundException;
import com.flux.model.*;
import com.flux.model.train.Train;
import lombok.NonNull;

import java.util.*;

public class CoachService {
    private static CoachService coachService;
    private final List<Integer> defaultCoachFillSequence = Arrays.asList(1,3,5,7,9,10,2,4,6,8);
    private final UserService userService;
    private final SeatService seatService;
    private final Map<String,List<Coach>> trainCoaches;

    private CoachService(@NonNull UserService userService, SeatService seatService) {
        this.userService = userService;
        this.seatService = seatService;
        trainCoaches = new HashMap<>();
    }

    public static CoachService getCoachService(UserService userService,SeatService seatService) {
        if(coachService == null){
            synchronized (CoachService.class) {
                if (coachService == null)
                coachService = new CoachService(userService, seatService);
            }
        }
        return coachService;
    }

    public Optional<Coach> allotCoach(@NonNull Train train) {
        List<Coach> coaches = train.getCoaches();
        return coaches.stream()
                .filter(coach -> !coach.isFilled())
                .findFirst();
    }

    public CoachStatus getCoachStatus(@NonNull User user,@NonNull Coach coach) {
        if (userService.isUserAdmin(user)) {
                List<Seat> seats = coach.getSeats();
                int totalSeats = seats.size();
                int totalFilledSeats = (int)seats.stream().filter(Seat::isBooked).count();
                return new CoachStatus(totalSeats, totalFilledSeats);
        }else{
            throw new NotAllowedException("User does not have permission to view coach status");
        }

    }
    public void viewCoachStatus(@NonNull String userId,@NonNull String trainNo, int coachNo){
        User user = userService.getUserById(userId).orElseThrow(()->new ResourceNotFoundException("User not found"));
       if(!trainCoaches.containsKey(trainNo) || !isCoachNoValid(coachNo)) return;
        List<Coach> coaches = trainCoaches.get(trainNo);
        System.out.println(getCoachStatus(user,coaches.get(coachNo-1)));
    }

    public List<Coach> createDefaultCoaches(@NonNull String trainNo) {
        List<Coach> coaches =new ArrayList<>();
        defaultCoachFillSequence.stream().forEach((i)-> {
            Coach coach = new Coach(i,seatService.populateDefaultSeats());
            coaches.add(coach);
        });
        trainCoaches.put(trainNo,coaches);
        return coaches;
    }
    private boolean isCoachNoValid(int coachNo){
        return coachNo > 0 && coachNo <= defaultCoachFillSequence.size();
    }



}
