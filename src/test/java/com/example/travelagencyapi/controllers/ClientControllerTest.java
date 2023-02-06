package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.services.ClientServiceImpl;
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
    @DisplayName("Testing getting all clients - no parameters")
    void getAllClients() throws Exception {

        given(clientService.getAllClients()).willReturn(clientDtoList);

        mockMvc.perform(get("/api/v1/clients/all").accept(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].firstname",is("Jan")))
                .andExpect(jsonPath("$[0].lastname",is("Pazyl")))
                .andExpect(jsonPath("$[0].client_url",is("someurl")))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8));

        //when working against real database the order of clients can be different!
    }

    @Test
    @DisplayName("Testing getting single client")
    void getClientById() throws Exception {
        given(clientService.getClientById(anyLong())).willReturn(clientDto);

        mockMvc.perform(get("/api/v1/clients/" + clientDto.getId()))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.lastname", is("Pazyl")))
                .andExpect(jsonPath("$.firstname", is("Jan")));

    }

    @Test
    @Disabled
    void createNewClient() throws Exception {


        String jsonData = "{"
                + "\"id\": \"1\","
                + "\"firstname\": \"Jan\","
                + "\"lastname\" : \"Pazyl\","
                + "\"offerDtoList\" : null,"
                + "\"client_url\" : \"someurl\""
                + "}";

        given(clientService.createNewClient(any())).willReturn(clientDto);

        mockMvc.perform(post("/api/v1/clients")
        .content(jsonData))
                .andExpect(status().isOk());

        //throws unsupported media type 415
        //todo check testing post request with sending json data content

    }

    @Test
    void saveClientById() {
    }

    @Test
    void patchClient() {
    }

    @Test
    @DisplayName("Testing delete client")
    void deleteClient() throws Exception {


        mockMvc.perform(delete("/api/v1/clients/1"))
                .andExpect(status().isOk());

        then(clientService).should().deleteClient(1L);
    }
}