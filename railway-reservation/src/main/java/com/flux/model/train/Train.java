package com.flux.model.train;

import com.flux.model.Coach;
import com.flux.model.Route;

import java.util.List;

public interface Train {
    String getTrainNumber();
    Route getRoute();
    List<Coach> getCoaches();

}
