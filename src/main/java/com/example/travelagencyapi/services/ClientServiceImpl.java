package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.ClientMapper;
import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.controllers.ClientController;
import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientMapper clientMapper) {
        this.clientRepository = clientRepository;
        this.clientMapper = clientMapper;
    }


    @Override
    public List<ClientDto> getAllClients() {
        return clientRepository.findAll()
                .stream()
                .map(client -> {
                    ClientDto clientDto = clientMapper.clientToClientDto(client);
                    clientDto.setClientUrl(createClientUrl(client.getId()));
                    return clientDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public ClientDto getClientById(Long id) {
        return clientRepository.findById(id)
                .map(client -> {
                    ClientDto clientDto = clientMapper.clientToClientDto(client);
                    clientDto.setClientUrl(createClientUrl(id));
                    return clientDto;
                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public ClientDto createNewClient(ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        Client savedClient = clientRepository.save(client);
        ClientDto returnDto = clientMapper.clientToClientDto(savedClient);
        returnDto.setClientUrl(createClientUrl(savedClient.getId()));
        return returnDto;
    }

    @Override
    public ClientDto saveClientById(Long id, ClientDto clientDto) {
        Client client = clientMapper.clientDtoToClient(clientDto);
        client.setId(id);
        Client savedClient = clientRepository.save(client);
        ClientDto returnDto = clientMapper.clientToClientDto(savedClient);
        returnDto.setClientUrl(createClientUrl(id));
        return returnDto;
    }

    @Override
    public ClientDto patchClient(Long id, ClientDto clientDto) {
        return clientRepository.findById(id)
                .map(client -> {

                        if(clientDto.getFirstname() != null){
                            client.setFirstname(clientDto.getFirstname());
                        }

                        if(clientDto.getLastname() != null){
                            client.setLastname(clientDto.getLastname());
                        }

//                        if(clientDto.getOfferDtoList() != null){
//                            client.setBookedOffers(clientDto.getOfferDtoList());
//                        } /todo Patching list of offers - if necessary!!


                        Client savedClient = clientRepository.save(client);

                        ClientDto returnDto = clientMapper.clientToClientDto(savedClient);

                        returnDto.setClientUrl(createClientUrl(id));

                        return returnDto;

                })
                .orElseThrow(RuntimeException::new);
    }

    @Override
    public void deleteClient(Long id) {
        clientRepository.deleteById(id);

    }

    private String createClientUrl(Long id){
        return ClientController.BASE_URL + "/" + id;
    }
}
