package com.example.travelagencyapi.domain;

import lombok.Data;

import java.util.List;


@Data
public class ClientRequest {

    private Float maxPricePerNight;
    private Integer maxNumberOfNights;
    private List<Continent> acceptedDestinations;
    private Boolean isDogRequested;
}
