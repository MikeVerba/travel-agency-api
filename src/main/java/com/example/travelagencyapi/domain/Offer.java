package com.example.travelagencyapi.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float pricePerNight;
    private Integer numberOfNights;
    private Continent continent;
    private Boolean isDogAllowed;
    private Boolean isOfferBooked;

    @ManyToOne
    @JoinColumn(name = "client_id")
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
        return isDogAllowed;
    }

    public void setDogAllowed(Boolean dogAllowed) {
        isDogAllowed = dogAllowed;
    }

    public Boolean getOfferBooked() {
        return isOfferBooked;
    }

    public void setOfferBooked(Boolean offerBooked) {
        isOfferBooked = offerBooked;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }
}
