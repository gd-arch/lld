package com.flux.model.train;

import com.flux.model.Coach;
import com.flux.model.Route;

import java.util.List;

public class ExpressTrain extends AbstractTrain{
    public ExpressTrain(String trainNumber, Route route, List<Coach> coaches) {
        super(trainNumber,route, coaches);
    }

}
