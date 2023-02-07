package com.example.travelagencyapi.controllers;


import com.example.travelagencyapi.services.BookingServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(BookingController.BASE_URL)
public class BookingController {
    public static final String BASE_URL = "api/v1/booking";
    private final BookingServiceImpl bookingService;

    @Autowired
    public BookingController(BookingServiceImpl bookingService) {
        this.bookingService = bookingService;
    }

    @RequestMapping(value = "/{client_id}/{offer_id}", method = RequestMethod.POST)
    public ResponseEntity<Void> bookOfferForClientId(@PathVariable("client_id") Long clientId, @PathVariable("offer_id") Long offerId) {
        bookingService.bookOfferForClientId(clientId, offerId);
        return new ResponseEntity<Void>(HttpStatus.OK);

    }
}
