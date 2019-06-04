package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.services.OfferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class OfferControllerTest {

    @Mock
    OfferServiceImpl offerService;

    @InjectMocks
    OfferController offerController;

    MockMvc mockMvc;

    List<Offer> offerList;

    List<OfferDto> offerDtoList;

    Offer offer;

    OfferDto offerDto;

    Client client;

    List<Client>clientList;

    ClientDto clientDto;



    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(offerController).build();

        offerList = new ArrayList<>();

        offerDtoList = new ArrayList<>();

        offer = new Offer();
        offer.setId(1L);
        offer.setClient(client);
        offer.setContinent(Continent.AF);
        offer.setIsDogAllowed(true);
        offer.setIsOfferBooked(false);
        offer.setPricePerNight(12.99F);
        offer.setNumberOfNights(4);


        client = new Client();
        client.setId(1L);
        client.setFirstname("Jan");
        client.setLastname("Pazyl");
        client.setBookedOffers(offerList);


        offerDto = new OfferDto();
        offerDto.setId(1L);
        offerDto.setOfferUrl("someurl");
        offerDto.setClient(client);
        offerDto.setContinent(Continent.AF);
        offerDto.setNumberOfNights(4);
        offerDto.setPricePerNight(12.99F);
        offerDto.setDogAllowed(true);

        offerDtoList.add(offerDto);


    }

    @Test
    void getAllOffers() throws Exception{

        given(offerService.getAllOffers()).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getAllUnBookedOffers() throws Exception {
        given(offerService.getAllUnbookedOffers()).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/unbooked"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

//    @Test
//    void getAllOffersForClient() throws Exception{
//
//        given(offerService.getAllBookedOffersForClient(any())).willReturn(offerDtoList);
//
//        mockMvc.perform(get("/api/v1/offers/booked-for-client"))
//                .andExpect(status().isOk())
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
//    }//todo learn how to send json with mockmvc

    @Test
    void getAllOffersQualifiedByConditions() {
    }

    @Test
    void getOfferById() {
    }

    @Test
    void createNewOffer() {
    }

    @Test
    void saveOfferById() {
    }

    @Test
    void patchOffer() {
    }

    @Test
    void deleteOffer() {
    }
}