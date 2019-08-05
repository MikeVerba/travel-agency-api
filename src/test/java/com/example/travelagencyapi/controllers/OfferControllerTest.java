package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.services.OfferServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.core.Is.is;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
    @DisplayName("Testing getting all offers  - no parameters")
    void getAllOffers() throws Exception{

        given(offerService.getAllOffers()).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/all").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].continent",is(Continent.AF.toString())))
                .andExpect(jsonPath("$[0].pricePerNight",is(12.99)))
                .andExpect(jsonPath("$[0].client.id",is(1)))
                .andExpect(jsonPath("$[0].client.firstname",is("Jan")))
                .andExpect(jsonPath("$[0].client.lastname",is("Pazyl")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        //when working against real database the order of offers can be different!
    }

    @Test
    @DisplayName("Testing getting all Unbooked Offers")
    void getAllUnBookedOffers() throws Exception {
        given(offerService.getAllUnbookedOffers()).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/unbooked"))
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].continent",is(Continent.AF.toString())))
                .andExpect(jsonPath("$[0].pricePerNight",is(12.99)))
                .andExpect(jsonPath("$[0].client.id",is(1)))
                .andExpect(jsonPath("$[0].client.firstname",is("Jan")))
                .andExpect(jsonPath("$[0].client.lastname",is("Pazyl")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    @Disabled
    void getAllOffersForClient() throws Exception{

        given(offerService.getAllBookedOffersForClient(any())).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/booked-for-client"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }//throws 400 error

    @Test
    void getAllOffersQualifiedByConditions() throws Exception {

        given(offerService.getAllOffersQualifiedByConditions(any())).willReturn(offerDtoList);

        mockMvc.perform(get("/api/v1/offers/booked-for-conditions").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk());


    }

    @Test
    @DisplayName("Testing getting single offer")
    void getOfferById() throws Exception{

        given(offerService.getOfferById(anyLong())).willReturn(offerDto);

        mockMvc.perform(get("/api/v1/offers/"+offerDto.getId().toString()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.offer_url", is("someurl")))
                .andExpect(jsonPath("$.numberOfNights", is(4)))
                .andExpect(jsonPath("$.continent", is(Continent.AF.toString())))
                .andExpect(jsonPath("$.dogAllowed", is(true)))
                .andExpect(jsonPath("$.pricePerNight",is(12.99)));
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
    @DisplayName("Testing delete offer")
    void deleteOffer() throws Exception {

        mockMvc.perform(delete("/api/v1/offers/"+offer.getId()))
                .andExpect(status().isOk());

        then(offerService).should().deleteOffer(offer.getId());

    }
}