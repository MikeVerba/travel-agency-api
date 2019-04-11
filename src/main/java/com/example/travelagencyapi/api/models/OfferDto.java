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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Float getPricePerNight() {
        return pricePerNight;
    }

    public void setPricePerNight(Float pricePerNight) {
        this.pricePerNight = pricePerNight;
    }

    public Integer getNumberOfNights() {
        return numberOfNights;
    }

    public void setNumberOfNights(Integer numberOfNights) {
        this.numberOfNights = numberOfNights;
    }

    public Continent getContinent() {
        return continent;
    }

    public void setContinent(Continent continent) {
        this.continent = continent;
    }

    public Boolean getDogAllowed() {
        return dogAllowed;
    }

    public void setDogAllowed(Boolean dogAllowed) {
        this.dogAllowed = dogAllowed;
    }

    public Boolean getOfferBooked() {
        return offerBooked;
    }

    public void setOfferBooked(Boolean offerBooked) {
        this.offerBooked = offerBooked;
    }

    public String getOfferUrl() {
        return offerUrl;
    }

    public void setOfferUrl(String offerUrl) {
        this.offerUrl = offerUrl;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
