package com.coachingeleven.coachingsoftware;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
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
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;
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
	
	private List<Contact> allContacts;
	private SimpleDateFormat dateFormatter;

	@PostConstruct
	public void init() {
		newContact = new Contact();
		newContactAddress = new Address();
		newContactCountry = new Country();
		roles = Role.values();
		allContacts = contactService.findAll();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
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
	
	public void updateContact(Contact contact, String birthdayFormatted) {
		if(contact != null) {
			String oldEmail = "";
			try {
				oldEmail = contactService.findContact(contact.getID()).getEmail();
				oldEmail = oldEmail != null ? oldEmail : "";
			} catch (ContactNotFoundException e2) {
				// Nothing to check here
			}
			if(checkForDuplicateEmail(contact.getEmail(), oldEmail)) {
				if(birthdayFormatted != null && !birthdayFormatted.isEmpty()) {
					// Parse string of date to date
					try {
						Calendar playerBirthdayCalendar = Calendar.getInstance();
						playerBirthdayCalendar.setTime(dateFormatter.parse(birthdayFormatted));
						contact.setBirthdate(playerBirthdayCalendar);
					} catch (ParseException e) {
						FacesContext facesContext = FacesContext.getCurrentInstance();
						// TODO: localization
						FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
						facesContext.addMessage("updatePlayerForm:datePickerUserAccountBirthday", facesMessage);
					}
				}
				try {
					contact.getAddress().setCountry(countryService.findCountry(contact.getAddress().getCountry().getName()));
				} catch (CountryNotFounException e1) {
					try {
						Country newCountry = new Country(contact.getAddress().getCountry().getName());
						contact.getAddress().setCountry(countryService.createCountry(newCountry));
					} catch (CountryAlreadyExistsException e) {
						// TODO: Initialize all country's on DB creation
					}
				}
				contactService.update(contact);
			}
			
		}
		
	}
	
	private boolean checkForDuplicateEmail(String newEmail, String oldEmail) {
		// Check if user has a new email and if that email is already occupied
		if(newEmail != null && !oldEmail.equals(newEmail)) {
			try {
				contactService.findContact(newEmail);
				FacesContext facesContext = FacesContext.getCurrentInstance();
				FacesMessage facesMessage = new FacesMessage("Email already exists", "Email already exists!");
				facesContext.addMessage("updateUserAccountContactForm:tc_email", facesMessage);
				return false;
			} catch (ContactNotFoundException e) {
				// Email is free
				return true;
			}
		}
		return true;
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

	public List<Contact> getAllContacts() {
		return allContacts;
	}

	public void setAllContacts(List<Contact> allContacts) {
		this.allContacts = allContacts;
	}

}
