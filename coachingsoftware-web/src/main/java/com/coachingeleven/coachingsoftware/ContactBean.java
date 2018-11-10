package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named("contactBean")
@RequestScoped
public class ContactBean implements Serializable {

	private static final long serialVersionUID = -7088899575105482224L;

	private static final Logger logger = Logger.getLogger(ContactBean.class.getName());

	@Inject
	private NavigationBean navigationBean;

	@EJB
	private ContactServiceRemote contactService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private CountryServiceRemote countryService;

	private Contact newContact;
	private Address newContactAddress;
	private Country newContactCountry;

	private Role[] roles;

	@PostConstruct
	public void init() {
		newContact = new Contact();
		newContactAddress = new Address();
		newContactCountry = new Country();
		roles = Role.values();
	}

	public String createContact() throws CountryAlreadyExistsException {
		try {
			newContactCountry = countryService.findCountry(newContactCountry.getName());
		} catch (CountryNotFounException e) {
			newContactCountry = countryService.createCountry(newContactCountry);
		}
		newContactAddress.setCountry(newContactCountry);
		newContact.setAddress(newContactAddress);
		try {
			newContact = contactService.createContact(newContact);
		} catch (ContactAlreadyExistsException e) {
			logger.log(Level.INFO, e.getMessage());
		}
		return navigationBean.redirectToAddressOverview();
	}

	public Contact getNewContact() {
		return newContact;
	}

	public void setNewContact(Contact newContact) {
		this.newContact = newContact;
	}

	public Address getNewContactAddress() {
		return newContactAddress;
	}

	public void setNewContactAddress(Address newContactAddress) {
		this.newContactAddress = newContactAddress;
	}

	public Country getNewContactCountry() {
		return newContactCountry;
	}

	public void setNewContactCountry(Country newContactCountry) {
		this.newContactCountry = newContactCountry;
	}

	public Role[] getRoles() {
		return roles;
	}
}
