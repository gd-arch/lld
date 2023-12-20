package com.flux.services;

import com.flux.exception.StationAlreadyExistsException;
import com.flux.model.Station;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class StationService {
    private static StationService stationService;
    private final List<Station> stations;

    private StationService() {
        this.stations = new ArrayList<>();
    }
    public static StationService getStationService() {
        if(stationService == null){
            synchronized (StationService.class){
                if(stationService == null){
                    return new StationService();
                }
            }
        }
        return stationService;
    }


    public List<Station> getStations() {
        return stations;
    }

    public Station createStation(String stationCode, String name) {
        if (getStationByCode(stationCode).isPresent()) {
            throw new StationAlreadyExistsException("Station with code " + stationCode + " already exists.");
        }
        Station newStation = new Station(stationCode, name);
        stations.add(newStation);
        return newStation;
    }

    public Optional<Station> getStationByCode(String stationCode) {
        return stations.stream().filter(station -> station.getStationCode().equals(stationCode)).findFirst();
    }

    public void updateStation(String stationCode, String newName) {
        getStationByCode(stationCode).ifPresent(station -> station.setName(newName));
    }

    public void deleteStationByCode(String stationCode) {
        stations.removeIf(station -> station.getStationCode().equals(stationCode));
    }
}
