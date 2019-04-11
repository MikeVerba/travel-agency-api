package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.models.OfferDto;

public interface BookingService {

    OfferDto bookOfferForClientId(Long clientId, Long offerId);

}
