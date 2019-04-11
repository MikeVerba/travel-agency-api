package com.example.travelagencyapi.api.mappers;

import com.example.travelagencyapi.api.models.OfferDto;
import com.example.travelagencyapi.domain.Offer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface OfferMapper {

    OfferMapper INSTANCE = Mappers.getMapper(OfferMapper.class);
    OfferDto offerToOfferDto(Offer offer);
    Offer offerDtoToOffer(OfferDto offerDto);
}
