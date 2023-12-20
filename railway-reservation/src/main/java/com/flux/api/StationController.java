package com.flux.api;

import com.flux.model.Station;
import com.flux.services.StationService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

@AllArgsConstructor
public class StationController {
    private final StationService stationService;

    public Station createStation(@NonNull String stationCode,@NonNull String name) {
        try {
            stationService.createStation(stationCode, name);
            System.out.println("Station created successfully.");
            return stationService.getStationByCode(stationCode).orElse(null);
        } catch (Exception exception) {
            System.out.println("Exception occurred: " + exception.getMessage());
            return null;
        }
    }

}