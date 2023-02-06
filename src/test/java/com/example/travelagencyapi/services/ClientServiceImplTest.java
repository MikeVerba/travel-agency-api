package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.ClientMapper;
import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.repositories.ClientRepository;
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
class ClientServiceImplTest {
    @Mock
    ClientRepository clientRepository;
    @Mock
    ClientMapper clientMapper;
    @InjectMocks
    ClientServiceImpl clientService;
    List<Offer> offerList = new ArrayList<>();
    Offer offer;
    OfferDto offerDto;
    Client client;
    List<Client> clientList = new ArrayList<>();
    ClientDto clientDto;

    @BeforeEach
    void setUp() {
        offer = createOffer();
        client = createClient();
        clientList.add(client);
        offerDto = createOfferDto();
        clientDto = createClientDto();
    }

    private ClientDto createClientDto() {
        ClientDto clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setClientUrl("someurl");
        clientDto.setFirstname("Jan");
        clientDto.setLastname("Pazyl");
        clientDto.setOfferDtoList(null);
        return clientDto;
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
    void getAllClients() {
        //given
        given(clientRepository.findAll()).willReturn(clientList);
        given(clientMapper.clientToClientDto(any())).willReturn(clientDto);
        //when
        List<ClientDto> returnList = clientService.getAllClients();
        //then
        then(clientRepository).should().findAll();
        then(clientMapper).should().clientToClientDto(any());
        assertEquals(returnList.get(0).getFirstname(), "Jan");
    }

    @Test
    void getClientById() {
        //given
        given(clientRepository.findById(1L)).willReturn(Optional.of(client));
        given(clientMapper.clientToClientDto(any())).willReturn(clientDto);
        //when
        ClientDto returnDto = clientService.getClientById(1L);
        //then
        then(clientRepository).should().findById(1L);
        then(clientMapper).should().clientToClientDto(any());
        assertEquals(returnDto, clientDto);
    }

    @Test
    void createNewClient() {
        //given
        given(clientRepository.save(any())).willReturn(client);
        given(clientMapper.clientDtoToClient(any())).willReturn(client);
        given(clientMapper.clientToClientDto(any())).willReturn(clientDto);
        //when
        ClientDto returnDto = clientService.createNewClient(clientDto);
        //then
        then(clientRepository).should().save(client);
        then(clientMapper).should().clientToClientDto(client);
        then(clientMapper).should().clientDtoToClient(clientDto);
        assertEquals(returnDto.getFirstname(), "Jan");
    }

    @Test
    void saveClientById() {
        //given
        given(clientRepository.save(any())).willReturn(client);
        given(clientMapper.clientToClientDto(any())).willReturn(clientDto);
        given(clientMapper.clientDtoToClient(any())).willReturn(client);
        //when
        ClientDto returnDto = clientService.saveClientById(1L, clientDto);
        //then
        then(clientRepository).should().save(client);
        then(clientMapper).should().clientDtoToClient(clientDto);
        then(clientMapper).should().clientToClientDto(client);
        assertEquals(returnDto.getFirstname(), "Jan");
    }

    @Test
    void patchClient() {
        //given
        given(clientRepository.findById(anyLong())).willReturn(Optional.of(client));
        given(clientMapper.clientToClientDto(any())).willReturn(clientDto);
        //when
        ClientDto returnDto = clientService.patchClient(1L, clientDto);
        //then
        then(clientRepository).should().save(client);
        then(clientMapper).should().clientToClientDto(any());
        assertEquals(returnDto.getFirstname(), "Jan");
    }

    @Test
    void deleteClient() {
        //when
        clientService.deleteClient(1L);
        //then
        then(clientRepository).should().deleteById(1L);
    }
}