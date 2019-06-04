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


}
