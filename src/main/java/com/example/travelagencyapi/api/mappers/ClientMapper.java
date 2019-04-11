package com.example.travelagencyapi.api.mappers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.domain.Client;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ClientMapper {

    ClientMapper INSTANCE = Mappers.getMapper(ClientMapper.class);
    Client clientDtoToClient(ClientDto clientDto);
    ClientDto clientToClientDto(Client client);
}
