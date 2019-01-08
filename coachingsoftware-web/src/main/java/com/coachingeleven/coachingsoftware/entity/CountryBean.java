package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;

@Named("countryBean")
@RequestScoped
public class CountryBean implements EntityBean<Country>, Serializable {

	private static final long serialVersionUID = 866516773351804539L;
	
	private static final Logger logger = Logger.getLogger(CountryBean.class.getName());
	
	@EJB
    private CountryServiceRemote countryService;
	
	private Country entity;
	private List<Country> entities;
	
	@PostConstruct
    public void init() {
		entities = countryService.findAll();
		// TODO: Create entities when setting up the database
		if(entities == null || entities.size() == 0) {
			for(String isoCountry : Locale.getISOCountries()) {
				Country newCountry = new Country();
				newCountry.setName(isoCountry);
				try {
					countryService.createCountry(newCountry);
				} catch (CountryAlreadyExistsException e) {
					logger.log(Level.INFO, "Could not create country '" + isoCountry + "' cause: " + e.getMessage());
				}
			}
			entities = countryService.findAll();
		}
    }
	
	@Override
	public Country getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Country entity) {
		this.entity = entity;
	}

	@Override
	public List<Country> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Country> entities) {
		this.entities = entities;
	}

}
