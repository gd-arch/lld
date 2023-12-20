package com.flux.services;

import com.flux.exception.RouteAlreadyExistException;
import com.flux.model.Route;
import com.flux.model.Station;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RouteService {
    private static RouteService routeService;
    private final List<Route> routes;

    private RouteService() {
        this.routes = new ArrayList<>();
    }

    public static RouteService getRouteService() {
        if(routeService == null){
            synchronized (RouteService.class){
                if(routeService == null){
                    return new RouteService();
                }
            }
        }
        return routeService;
    }

    public String createRoute(@NonNull String routeNumber, @NonNull List<Station> stations) {
        if(getRouteByNumber(routeNumber).isPresent()){
            throw new RouteAlreadyExistException();
        }
        Route newRoute = new Route(routeNumber, stations);
        routes.add(newRoute);
        return newRoute.getRouteNumber();
    }

    public Optional<Route> getRouteByNumber(@NonNull String routeNumber) {
        return routes.stream().filter(route -> route.getRouteNumber().equals(routeNumber)).findFirst();
    }

    public void deleteRouteByNumber(@NonNull String routeNumber) {
        routes.removeIf(route -> route.getRouteNumber().equals(routeNumber));
    }
}
