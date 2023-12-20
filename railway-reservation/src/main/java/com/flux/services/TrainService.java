package com.flux.services;

import com.flux.exception.ResourceNotFoundException;
import com.flux.exception.TrainAlreadyExistsException;
import com.flux.factory.TrainFactory;
import com.flux.model.Coach;
import com.flux.model.Route;
import com.flux.model.train.Train;
import com.flux.model.train.TrainType;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TrainService {
    private static TrainService trainService;
    private final TrainFactory trainFactory;
    private final RouteService routeService;
    private final CoachService coachService;
    private final List<Train> trains;

    private TrainService(TrainFactory trainFactory, RouteService routeService, CoachService coachService) {
        this.trainFactory = trainFactory;
        this.routeService = routeService;
        this.coachService = coachService;
        this.trains = new ArrayList<>();
    }
    public static TrainService getTrainsService(TrainFactory trainFactory, RouteService routeService, CoachService coachService) {
        if (trainService == null) {
            synchronized (TrainService.class) {
                if (trainService == null) {
                    trainService = new TrainService(trainFactory, routeService, coachService);
                }
            }
        }
        return trainService;
    }

    public Optional<Train> getTrainByNumber(@NonNull String trainNumber) {
        return trains.stream()
                .filter(train -> train.getTrainNumber().equals(trainNumber))
                .findFirst();
    }

    public String createTrain(@NonNull TrainType trainType,@NonNull String trainNo,@NonNull String routeNo) {
        if (getTrainByNumber(trainNo).isPresent()) {
            throw new TrainAlreadyExistsException("Train with number " + trainNo + " already exists.");
        }
        Route route = routeService.getRouteByNumber(routeNo).orElseThrow(() -> new ResourceNotFoundException("Route with number "+ routeNo+" not found"));
        Train train = trainFactory.createTrain(trainType, trainNo, route,coachService.createDefaultCoaches(trainNo));
        trains.add(train);
        return train.getTrainNumber();
    }
    public Optional<List<Coach>> getCoachesByTrainNo(@NonNull String trainNo){
        Train train = getTrainByNumber(trainNo).orElseThrow(()-> new ResourceNotFoundException());
        return Optional.ofNullable(train.getCoaches());
    }
}
