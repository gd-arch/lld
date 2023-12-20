package com.flux.factory;

import com.flux.model.Coach;
import com.flux.model.Route;
import com.flux.model.train.ExpressTrain;
import com.flux.model.train.LocalTrain;
import com.flux.model.train.Train;
import com.flux.model.train.TrainType;

import java.util.List;

public class TrainFactory {
    public Train createTrain(TrainType trainType, String trainNumber, Route route, List<Coach> coaches){
        switch (trainType){
            case EXPRESS:
                return new ExpressTrain(trainNumber,route,coaches);
            default:
                return new LocalTrain(trainNumber,route,coaches);
        }
    }
}
