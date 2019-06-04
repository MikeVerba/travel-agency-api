package com.example.travelagencyapi.services;

import com.example.travelagencyapi.api.mappers.OfferMapper;
import com.example.travelagencyapi.api.models.ClientDto;
import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.controllers.OfferController;
import com.example.travelagencyapi.domain.Offer;
import com.example.travelagencyapi.repositories.OfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OfferServiceImpl implements OfferService {

    private final OfferRepository offerRepository;
    private final OfferMapper offerMapper;


    @Autowired
    public OfferServiceImpl(OfferRepository offerRepository, OfferMapper offerMapper) {
        this.offerRepository = offerRepository;
        this.offerMapper = offerMapper;

    }

    @Override
    public List<OfferDto> getAllOffers() {
        return offerRepository.findAll()
                .stream()
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerToOfferDto(offer);
                    offerDto.setOfferUrl(createOfferUrl(offer.getId()));
                    return offerDto;
                }).collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> getAllUnbookedOffers() {

        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getClient() == null)
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerToOfferDto(offer);
                    offerDto.setOfferUrl(createOfferUrl(offer.getId()));
                    return offerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> getAllOffersQualifiedByConditions(OfferDto conditionsOfferDto) {
        return offerRepository.findAll()
                .stream()
                .filter(offer -> !offer.getIsOfferBooked())
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerToOfferDto(offer);
                    offerDto.setOfferUrl(createOfferUrl(offer.getId()));
                    return offerDto;
                })
                .filter(offerDto -> {

                    if(
                            offerDto.getPricePerNight() <= conditionsOfferDto.getPricePerNight() ||
                                    offerDto.getNumberOfNights() >= conditionsOfferDto.getNumberOfNights() ||
                                    offerDto.getContinent().equals(conditionsOfferDto.getContinent()) ||
                                    offerDto.getDogAllowed().equals(conditionsOfferDto.getDogAllowed())

                    ) return true;
                    else return false;

                })
                .collect(Collectors.toList());
    }

    @Override
    public List<OfferDto> getAllBookedOffersForClient(ClientDto clientDto) {


        return offerRepository.findAll()
                .stream()
                .filter(offer -> offer.getClient().getId().equals(clientDto.getId()))
                .map(offer -> {
                    OfferDto offerDto = offerMapper.offerToOfferDto(offer);
                    offerDto.setOfferUrl(createOfferUrl(offer.getId()));
                    return offerDto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public OfferDto getOfferById(Long id) {
        return offerRepository.findById(id)
                .map(offer->{
                    OfferDto offerDto = offerMapper.offerToOfferDto(offer);
                    offerDto.setOfferUrl(createOfferUrl(id));
                    return offerDto;
                }).orElseThrow(RuntimeException::new);

    }

    @Override
    public OfferDto createNewOffer(OfferDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(offerDto);
        Offer savedOffer = offerRepository.save(offer);
        OfferDto returnDto = offerMapper.offerToOfferDto(savedOffer);
        returnDto.setOfferUrl(createOfferUrl(savedOffer.getId()));
        return returnDto;
    }

    @Override
    public OfferDto saveOfferById(Long id, OfferDto offerDto) {
        Offer offer = offerMapper.offerDtoToOffer(offerDto);
        offer.setId(id);
        Offer savedOffer = offerRepository.save(offer);
        OfferDto returnDto = offerMapper.offerToOfferDto(savedOffer);
        returnDto.setOfferUrl(createOfferUrl(id));
        return returnDto;
    }

    @Override
    public OfferDto patchOffer(Long id, OfferDto offerDto) {
        return offerRepository.findById(id)
                .map(offer -> {
                   if(offerDto.getDogAllowed() != null){
                       offer.setIsDogAllowed(offerDto.getDogAllowed());
                   }

                   if(offerDto.getPricePerNight() != null){
                        offer.setPricePerNight(offerDto.getPricePerNight());
                   }

                    if(offerDto.getNumberOfNights() != null){
                        offer.setNumberOfNights(offerDto.getNumberOfNights());
                    }
                    if(offerDto.getContinent() != null){
                        offer.setContinent(offerDto.getContinent());
                    }
                    if(offerDto.getOfferBooked() != null){
                        offer.setIsOfferBooked(offerDto.getOfferBooked());
                    }

                    Offer savedOffer = offerRepository.save(offer);

                    OfferDto returnDto = offerMapper.offerToOfferDto(savedOffer);

                    returnDto.setOfferUrl(createOfferUrl(id));

                    return returnDto;

                })
                .orElseThrow(RuntimeException::new);


    }

    @Override
    public void deleteOffer(Long id) {
        offerRepository.deleteById(id);

    }

    private String createOfferUrl(Long id){
        return OfferController.BASE_URL + "/" + id;
    }
}
