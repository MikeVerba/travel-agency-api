package com.example.travelagencyapi.controllers;

import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.services.ClientServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(ClientController.BASE_URL)
public class ClientController {
    public static final String BASE_URL = "api/v1/clients";

    private final ClientServiceImpl clientService;

    @Autowired
    public ClientController(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<ClientDto>> getAllClients(){
        return new ResponseEntity<>(clientService.getAllClients(),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientDto> getClientById(@PathVariable Long id){
        return new ResponseEntity<>(clientService.getClientById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<ClientDto> createNewClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.createNewClient(clientDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ClientDto> saveClientById(@PathVariable Long id, @RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.saveClientById(id, clientDto),HttpStatus.OK);
    }
    @PatchMapping("/{id}")
    public ResponseEntity<ClientDto> patchClient(@PathVariable Long id,@RequestBody ClientDto clientDto){
        return new ResponseEntity<>(clientService.patchClient(id, clientDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable Long id){
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
