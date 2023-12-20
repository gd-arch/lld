package com.flux.model.train;

import com.flux.model.Coach;
import com.flux.model.Route;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractTrain implements Train {
    protected String trainNumber;
    protected Route route;
    protected List<Coach> coaches;

    public AbstractTrain(String trainNumber, Route route, List<Coach> coaches) {
        this.trainNumber = trainNumber;
        this.route = route;
        this.coaches = coaches;
    }
    public String getTrainNumber() {
        return trainNumber;
    }

    public Route getRoute() {
        return route;
    }
    public List<Coach> getCoaches() {
        return coaches;
    }

}