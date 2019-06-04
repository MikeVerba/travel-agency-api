package com.example.travelagencyapi.api.models;

import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class OfferDto {

    private Long id;

    //@JsonProperty("price_per_night")
    private Float pricePerNight;
    //@JsonProperty("number_of_nights")
    private Integer numberOfNights;
    private Continent continent;
    //@JsonProperty("dog_allowed")
    private Boolean dogAllowed;
    //@JsonProperty("offer_booked")
    private Boolean offerBooked;

    @JsonProperty("offer_url")
    private String offerUrl;

    private Client client;

}
