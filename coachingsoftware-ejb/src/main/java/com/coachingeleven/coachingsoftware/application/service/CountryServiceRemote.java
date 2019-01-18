package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface CountryServiceRemote {
    Country createCountry(Country country) throws CountryAlreadyExistsException;
    Country findCountry(String name) throws CountryNotFounException;
    boolean deleteCountry(Country country);
    List<Country> findAll();
}
