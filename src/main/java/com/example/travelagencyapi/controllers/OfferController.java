package com.example.travelagencyapi.controllers;


import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.services.OfferServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(OfferController.BASE_URL)
public class OfferController {
    public static final String BASE_URL = "api/v1/offers";

    private final OfferServiceImpl offerService;

    @Autowired
    public OfferController(OfferServiceImpl offerService) {
        this.offerService = offerService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<OfferDto>> getAllOffers(){
        return new ResponseEntity<List<OfferDto>>(offerService.getAllOffers(),HttpStatus.OK);
    }

    @GetMapping("/unbooked")
    public ResponseEntity<List<OfferDto>> getAllUnBookedOffers(){
        return new ResponseEntity<List<OfferDto>>(offerService.getAllUnbookedOffers(),HttpStatus.OK);
    }

    @GetMapping("/booked-for-client")
    public ResponseEntity<List<OfferDto>> getAllOffersForClient(@RequestBody ClientDto clientDto){
        return new ResponseEntity<List<OfferDto>>(offerService.getAllBookedOffersForClient(clientDto),HttpStatus.OK);
    }

    @GetMapping("/booked-for-conditions")
    public ResponseEntity<List<OfferDto>> getAllOffersQualifiedByConditions(@RequestBody OfferDto conditionsOfferDto){

        return new ResponseEntity<List<OfferDto>>(offerService.getAllOffersQualifiedByConditions(conditionsOfferDto), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OfferDto> getOfferById(@PathVariable Long id){
        return new ResponseEntity<OfferDto>(offerService.getOfferById(id),HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<OfferDto> createNewOffer(@RequestBody OfferDto offerDto){
        return new ResponseEntity<OfferDto>(offerService.createNewOffer(offerDto),HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OfferDto> saveOfferById(@PathVariable Long id,@RequestBody OfferDto offerDto){
        return new ResponseEntity<OfferDto>(offerService.saveOfferById(id,offerDto),HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<OfferDto> patchOffer (@PathVariable Long id, @RequestBody OfferDto offerDto){
        return new ResponseEntity<OfferDto>(offerService.patchOffer(id, offerDto),HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOffer (@PathVariable Long id){
        offerService.deleteOffer(id);
        return new ResponseEntity<Void>(HttpStatus.OK);
    }
}
