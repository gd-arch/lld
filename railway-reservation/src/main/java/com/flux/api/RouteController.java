package com.flux.api;

import com.flux.model.Route;
import com.flux.model.Station;
import com.flux.services.RouteService;
import lombok.AllArgsConstructor;
import lombok.NonNull;

import java.util.List;
@AllArgsConstructor
public class RouteController {
    private final RouteService routeService;
    public String createRoute(@NonNull String routeNumber, @NonNull List<Station> stations){
        try {
            return routeService.createRoute(routeNumber,stations);
        }catch (Exception exception){
            System.out.println("Exception occured:"+exception.getMessage());
            return null;
        }
    }
}
