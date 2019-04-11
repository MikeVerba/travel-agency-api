package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.ClientMapper;
import com.example.travelagencyapi.api.mappers.OfferMapper;
import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.controllers.ClientController;
import com.example.travelagencyapi.controllers.OfferController;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.repositories.ClientRepository;
import com.example.travelagencyapi.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class BookingServiceImpl implements BookingService {

    private final OfferServiceImpl offerService;
    private final ClientServiceImpl clientService;
    private final OfferRepository offerRepository;
    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final OfferMapper offerMapper;

    @Autowired
    public BookingServiceImpl(OfferServiceImpl offerService,
                              ClientServiceImpl clientService,
                              OfferRepository offerRepository,
                              ClientRepository clientRepository,
                              ClientMapper clientMapper,
                              OfferMapper offerMapper) {

        this.offerService = offerService;
        this.clientService = clientService;
        this.offerRepository = offerRepository;
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
        this.offerMapper = offerMapper;

    }

    @Override
    public OfferDto bookOfferForClientId(Long clientId, Long offerId) {



        Client client = clientRepository.findById(clientId).get();

        return offerRepository.findById(offerId)
                .map(offer -> {

                    offer.setOfferBooked(true);
                    offer.setClient(client);
                    Offer savedOffer = offerRepository.save(offer);
                    return offerMapper.offerToOfferDto(savedOffer);
                })
                .orElseThrow(RuntimeException::new);

    }

    private String createOfferUrl(Long id){
        return OfferController.BASE_URL + "/" + id;
    }

    private String createClientUrl(Long id){
        return ClientController.BASE_URL + "/" + id;
    }
}
