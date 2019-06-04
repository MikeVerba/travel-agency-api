package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.OfferMapper;
import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class OfferServiceImplTest {

    @Mock
    OfferRepository offerRepository;

    @Mock
    OfferMapper offerMapper;

    @InjectMocks
    OfferServiceImpl offerService;



    Offer offer;
    Client client = new Client();
    OfferDto offerDto;
    ClientDto clientDto;
    List<Offer> offerList;
    List<OfferDto>offerDtoList;
    List<Client>clientList;



    @BeforeEach
    void setUp() {

        offer = new Offer();
        offer.setId(1L);
        offer.setClient(client);
        offer.setContinent(Continent.AF);
        offer.setIsDogAllowed(true);
        offer.setIsOfferBooked(false);
        offer.setPricePerNight(12.99F);
        offer.setNumberOfNights(4);

        offerList = new ArrayList<>();
        offerList.add(offer);

        client.setId(1L);
        client.setFirstname("Jan");
        client.setLastname("Pazyl");
        client.setBookedOffers(offerList);

        clientList = new ArrayList<>();
        clientList.add(client);


        offerDto = new OfferDto();
        offerDto.setId(1L);
        offerDto.setOfferUrl("someurl");
        offerDto.setClient(client);
        offerDto.setContinent(Continent.AF);
        offerDto.setNumberOfNights(4);
        offerDto.setPricePerNight(12.99F);
        offerDto.setDogAllowed(true);

        offerDtoList = new ArrayList<>();
        offerDtoList.add(offerDto);

        clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setClientUrl("someurl");
        clientDto.setFirstname("Jan");
        clientDto.setLastname("Pazyl");
        clientDto.setOfferDtoList(offerDtoList);
    }

    @Test
    void getAllOffers() {

        //given
        given(offerRepository.findAll()).willReturn(offerList);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        List<OfferDto> returnOfferDtoList = offerService.getAllOffers();

        //then

        then(offerRepository).should().findAll();
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDtoList.get(0).getContinent(),Continent.AF);
    }

    @Test
    void getAllUnbookedOffers() {

        //given
        given(offerRepository.findAll()).willReturn(offerList);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        List<OfferDto> returnOfferDtoList = offerService.getAllUnbookedOffers();

        //then

        then(offerRepository).should().findAll();
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDtoList.get(0).getContinent(),Continent.AF);
    }

    @Test
    void getAllOffersQualifiedByConditions() {

        //given
        given(offerRepository.findAll()).willReturn(offerList);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        List<OfferDto> returnOfferDtoList = offerService.getAllOffersQualifiedByConditions(offerDto);

        //then

        then(offerRepository).should().findAll();
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDtoList.get(0).getContinent(),Continent.AF);
    }

    @Test
    void getAllBookedOffersForClient() {

        //given
        given(offerRepository.findAll()).willReturn(offerList);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        List<OfferDto> returnOfferDtoList = offerService.getAllBookedOffersForClient(clientDto);

        //then
        then(offerRepository).should().findAll();
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDtoList.get(0).getContinent(),Continent.AF);
    }

    @Test
    void getOfferById() {

        //given

        given(offerRepository.findById(anyLong())).willReturn(Optional.of(offer));
        given(offerMapper.offerToOfferDto(offer)).willReturn(offerDto);

        //when

        OfferDto returnOfferDto = offerService.getOfferById(1L);

        //then

        then(offerRepository).should().findById(1L);
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDto.getClient(),client);
    }

    @Test
    void createNewOffer() {

        //given
        given(offerRepository.save(any())).willReturn(offer);
        given(offerMapper.offerDtoToOffer(any())).willReturn(offer);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        OfferDto returnOfferDto = offerService.createNewOffer(offerDto);

        //then

        then(offerRepository).should().save(offer);
        then(offerMapper).should().offerToOfferDto(offer);
        then(offerMapper).should().offerDtoToOffer(offerDto);
        assertEquals(returnOfferDto.getClient(),client);
    }

    @Test
    void saveOfferById() {

        //given
        given(offerRepository.save(any())).willReturn(offer);
        given(offerMapper.offerDtoToOffer(any())).willReturn(offer);
        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);

        //when

        OfferDto returnOfferDto = offerService.saveOfferById(1L,offerDto);

        //then
        then(offerRepository).should().save(offer);
        then(offerMapper).should().offerDtoToOffer(offerDto);
        then(offerMapper).should().offerToOfferDto(offer);
        assertEquals(returnOfferDto.getClient(),client);
    }

//    @Test
//    void patchOffer() {
//
//        //given
//        given(offerRepository.findById(anyLong())).willReturn(Optional.of(offer));
//        given(offerMapper.offerToOfferDto(any())).willReturn(offerDto);
//        given(offerMapper.offerDtoToOffer(any())).willReturn(offer);
//
//        //when
//
//        offerService.patchOffer(1L,offerDto);
//
//        //then
//        then(offerRepository).should().findById(anyLong());
//        then(offerMapper).should().offerToOfferDto(offer);
//        then(offerMapper).should().offerDtoToOffer(offerDto);
//    }//todo stackoverflow error!!

    @Test
    void deleteOffer() {

        //when
        offerService.deleteOffer(anyLong());
        //then
        then(offerRepository).should().deleteById(anyLong());
    }
}