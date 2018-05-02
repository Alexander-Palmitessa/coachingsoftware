package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import javax.ejb.Remote;

@Remote
public interface CountryServiceRemote {
    void createCountry(Country country);
    Country findCountry(String name) throws CountryNotFounException;
}
