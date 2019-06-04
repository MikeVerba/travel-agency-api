package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.services.ClientServiceImpl;
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


import static org.mockito.ArgumentMatchers.anyLong;
import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class ClientControllerTest {

    @Mock
    ClientServiceImpl clientService;

    @InjectMocks
    ClientController clientController;

    MockMvc mockMvc;

    List<ClientDto> clientDtoList;

    ClientDto clientDto;

    @BeforeEach
    void setUp() {
        clientDto = new ClientDto();
        clientDto.setId(1L);
        clientDto.setClientUrl("someurl");
        clientDto.setFirstname("Jan");
        clientDto.setLastname("Pazyl");
        clientDto.setOfferDtoList(null);

        clientDtoList = new ArrayList<>();
        clientDtoList.add(clientDto);

        mockMvc = MockMvcBuilders.standaloneSetup(clientController).build();
    }

    @Test
    void getAllClients() throws Exception {

        given(clientService.getAllClients()).willReturn(clientDtoList);

        mockMvc.perform(get("/api/v1/clients/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));
    }

    @Test
    void getClientById() throws Exception {
        given(clientService.getClientById(anyLong())).willReturn(clientDto);

        mockMvc.perform(get("/api/v1/clients/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(1)));
    }

    @Test
    void createNewClient() throws Exception {
       //todo post request testing with MockMvc
    }

    @Test
    void saveClientById() {
    }

    @Test
    void patchClient() {
    }

    @Test
    void deleteClient() throws Exception {


        mockMvc.perform(delete("/api/v1/clients/1"))
                .andExpect(status().isOk());

        then(clientService).should().deleteClient(1L);
    }
}