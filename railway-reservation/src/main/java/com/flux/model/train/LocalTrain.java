package com.flux.model.train;

import com.flux.model.Coach;
import com.flux.model.Route;

import java.util.List;

public class LocalTrain extends AbstractTrain{

    public LocalTrain(String trainNumber, Route route, List<Coach> coaches) {
        super(trainNumber,route,coaches);
    }

}
