package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.models.ClientDto;

import java.util.List;

public interface ClientService {

    List<ClientDto> getAllClients();

    ClientDto getClientById(Long id);

    ClientDto createNewClient(ClientDto clientDto);

    ClientDto saveClientById(Long id,ClientDto clientDto);

    ClientDto patchClient(Long id,ClientDto clientDto);

    void deleteClient(Long id);

}
