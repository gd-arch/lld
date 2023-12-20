package com.flux.model;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Route {
    @NonNull
    private final String routeNumber;
    @NonNull
    private final List<Station> stations;

}
