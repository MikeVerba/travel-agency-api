package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.OfferMapper;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.repositories.ClientRepository;
import com.example.travelagencyapi.repositories.OfferRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class BookingServiceImplTest {

    @Mock
    OfferRepository offerRepository;

    @Mock
    ClientRepository clientRepository;

    @InjectMocks
    BookingServiceImpl bookingService;

    List<Offer> offerList = new ArrayList<>();
    Offer offer;

    OfferDto offerDto;

    Client client;

    @Mock
    OfferMapper offerMapper;

    @BeforeEach
    void setUp() {
        offer = createOffer();
        client = createClient();
        offerDto = createOfferDto();
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

    private Offer createOffer() {
        Offer offer = new Offer();
        offer.setId(1L);
        offer.setClient(client);
        offer.setContinent(Continent.AF);
        offer.setIsDogAllowed(true);
        offer.setIsOfferBooked(false);
        offer.setPricePerNight(12.99F);
        offer.setNumberOfNights(4);
        return offer;
    }

    @Test
    void bookOfferForClientId() {
        //given
        given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        given(offerRepository.findById(1L)).willReturn(Optional.of(offer));
        given(offerRepository.save(any())).willReturn(offer);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when
        OfferDto returnDto = bookingService.bookOfferForClientId(1L, 1L);

        //then
        then(clientRepository).should().findById(anyLong());
        assertEquals(returnDto.getClient(), client);
    }
}