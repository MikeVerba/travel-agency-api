package com.example.travelagencyapi.services;


import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;


import java.util.List;

public interface OfferService {

    List<OfferDto> getAllOffers();

    List<OfferDto> getAllUnbookedOffers();

    List<OfferDto> getAllOffersQualifiedByConditions(OfferDto conditionsOfferDto);

    List<OfferDto> getAllBookedOffersForClient(ClientDto clientDto);

    OfferDto getOfferById(Long id);

    OfferDto createNewOffer(OfferDto offerDto);

    OfferDto saveOfferById(Long id,OfferDto offerDto);

    OfferDto patchOffer(Long id,OfferDto offerDto);

    void deleteOffer(Long id);

}
