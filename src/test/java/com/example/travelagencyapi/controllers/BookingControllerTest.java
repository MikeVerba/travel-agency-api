package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.services.BookingServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
class BookingControllerTest {
    @Mock
    BookingServiceImpl bookingService;
    @InjectMocks
    BookingController bookingController;
    MockMvc mockMvc;
    List<Offer> offerList;
    Offer offer;
    OfferDto offerDto;
    Client client;
    List<Client> clientList;
    ClientDto clientDto;

    @BeforeEach
    void setUp() {
        offerList = createOfferList();
        client = createClient();
        offerDto = createOfferDto();
        mockMvc = MockMvcBuilders.standaloneSetup(bookingController).build();
    }

    private OfferDto createOfferDto() {
        OfferDto offerDto = new OfferDto();
        offerDto.setId(1L);
        offerDto.setOfferUrl("someurl");
        offerDto.setClient(client);
        offerDto.setContinent(Continent.AF);
        offerDto.setNumberOfNights(4);
        offerDto.setPricePerNight(12.99F);
        offerDto.setDogAllowed(true);
        return offerDto;
    }

    private Client createClient() {
        Client client = new Client();
        client.setId(1L);
        client.setFirstname("Jan");
        client.setLastname("Pazyl");
        client.setBookedOffers(offerList);
        return client;
    }

    private List<Offer> createOfferList() {
        List<Offer> offerList = new ArrayList<>();
        offer = new Offer();
        offer.setId(1L);
        offer.setClient(client);
        offer.setContinent(Continent.AF);
        offer.setIsDogAllowed(true);
        offer.setIsOfferBooked(false);
        offer.setPricePerNight(12.99F);
        offer.setNumberOfNights(4);
        return offerList;
    }

    @Test
    void bookOfferForClientId() throws Exception {
        given(bookingService.bookOfferForClientId(anyLong(), anyLong())).willReturn(offerDto);

        mockMvc.perform(post("/api/v1/booking/1/1/")
                        .param("client_id", "1")
                        .param("offer_id", "1"))
                .andExpect(status().isOk());
    }
}