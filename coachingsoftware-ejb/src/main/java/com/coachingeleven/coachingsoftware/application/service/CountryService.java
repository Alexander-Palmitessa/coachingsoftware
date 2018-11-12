package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.repository.CountryRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "CountryService")
@TransactionAttribute(REQUIRED)
public class CountryService implements CountryServiceRemote{

    private static final Logger logger = Logger.getLogger(CountryService.class.getName());

    @EJB
    private CountryRepository countryRepository;

    @Override
    public Country createCountry(Country country) throws CountryAlreadyExistsException {
        logger.log(Level.INFO, "Creating country with name " + country.getName() + " and id ''{0}''", country.getID());
        if(countryRepository.find(Country.class, country.getID()) != null){
            logger.log(Level.INFO, "Country with id " + country.getID()+ " already exists");
            throw new CountryAlreadyExistsException();
        }
        if(countryRepository.find(country.getName()) != null){
            logger.log(Level.INFO, "Country with name " + country.getName() + " already exists");
            throw new CountryAlreadyExistsException();
        }
        return countryRepository.persist(country);
    }

    @Override
    public Country findCountry(String name) throws CountryNotFounException {
        logger.log(Level.INFO, "Finding Country with name " + name);
        Country country = countryRepository.find(name);
        if (country == null) {
            logger.log(Level.INFO, "Country not found");
            throw new CountryNotFounException();
        }
        return country;
    }

    @Override
    public boolean deleteCountry(Country country) {
        return countryRepository.delete(Country.class, country.getID());
    }

	@Override
	public List<Country> findAll() {
		return countryRepository.findAll(Country.class);
	}
}
