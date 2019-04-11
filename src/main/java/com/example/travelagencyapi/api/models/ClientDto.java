package com.example.travelagencyapi.api.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ClientDto {

    private Long id;
    private String firstname;
    private String lastname;

    @JsonProperty("client_url")
    private String clientUrl;

    private List<OfferDto>offerDtoList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getClientUrl() {
        return clientUrl;
    }

    public void setClientUrl(String clientUrl) {
        this.clientUrl = clientUrl;
    }

    public List<OfferDto> getOfferDtoList() {
        return offerDtoList;
    }

    public void setOfferDtoList(List<OfferDto> offerDtoList) {
        this.offerDtoList = offerDtoList;
    }
}
