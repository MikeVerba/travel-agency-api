package com.example.travelagencyapi.bootstrap;

import com.example.travelagencyapi.domain.Client;
import com.example.travelagencyapi.domain.Continent;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.repositories.ClientRepository;
import com.example.travelagencyapi.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class Bootstrap implements CommandLineRunner {

    private ClientRepository clientRepository;
    private OfferRepository offerRepository;

    @Autowired
    public Bootstrap(ClientRepository clientRepository, OfferRepository offerRepository) {
        this.clientRepository = clientRepository;
        this.offerRepository = offerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadClientData();
        loadOfferData();

    }

    private void loadOfferData(){

        Offer offer1 = new Offer();
        offer1.setNumberOfNights(2);
        offer1.setPricePerNight(219.99F);
        offer1.setContinent(Continent.AF);
        offer1.setIsDogAllowed(false);
        offer1.setIsOfferBooked(false);
        offerRepository.save(offer1);

        Offer offer2 = new Offer();
        offer2.setNumberOfNights(4);
        offer2.setPricePerNight(300.99F);
        offer2.setContinent(Continent.EU);
        offer2.setIsDogAllowed(true);
        offer2.setIsOfferBooked(false);
        offerRepository.save(offer2);

        Offer offer3 = new Offer();
        offer3.setNumberOfNights(7);
        offer3.setPricePerNight(500.99F);
        offer3.setContinent(Continent.AS);
        offer3.setIsDogAllowed(false);
        offer3.setIsOfferBooked(false);
        offerRepository.save(offer3);


        System.out.println("Offer Data loaded: " + offerRepository.count());

    }

    private void loadClientData(){

        Client client1 = new Client();
        client1.setFirstname("John");
        client1.setLastname("Smith");

        clientRepository.save(client1);

        Client client2 = new Client();
        client2.setFirstname("James");
        client2.setLastname("Bond");

        clientRepository.save(client2);

        Client client3 = new Client();
        client3.setFirstname("Jane");
        client3.setLastname("Austin");

        clientRepository.save(client3);

        Client client4 = new Client();
        client4.setFirstname("Klozeida");
        client4.setLastname("Van Klompf");

        clientRepository.save(client4);


        System.out.println("Client Data loaded: " + clientRepository.count());

    }
}
