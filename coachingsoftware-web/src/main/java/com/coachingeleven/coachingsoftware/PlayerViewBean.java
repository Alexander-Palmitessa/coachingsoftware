package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;

@Named(value = "playerViewBean")
@SessionScoped
public class PlayerViewBean implements Serializable {
	
	private static final long serialVersionUID = -4038586817749078253L;

	@Inject
	private NavigationBean navigationBean;
	
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private CountryServiceRemote countryService;
	
	private Player currentPlayer;
	
	private SimpleDateFormat dateFormatter;
	
	private String birthdayFormatted;
	private String oldEmailAddress;
	
	@PostConstruct
	public void init() {
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	public String resetCurrentPlayer(int playerID) {
		try {
			currentPlayer = playerService.findPlayer(playerID);
			// Format date for GUI
			if(currentPlayer.getBirthdate() != null) birthdayFormatted = dateFormatter.format(currentPlayer.getBirthdate().getTime());
			// Create empty address if player doesn't have one
			if(currentPlayer.getAddress() == null) currentPlayer.setAddress(new Address());
			// Create new country if player doesn't have one assigned
			if(currentPlayer.getAddress().getCountry() == null) currentPlayer.getAddress().setCountry(new Country());
			oldEmailAddress = currentPlayer.getEmail();
			return navigationBean.redirectToPlayer();
		} catch (PlayerNotFoundException e) {
			return navigationBean.redirectToCurrentPlayersOverview();
		}
	}
	
	public void updateCurrentPlayer() {
		if(checkForDuplicateEmail()) {
			try {
				// Set new birthday for current player
				if(birthdayFormatted != null && !birthdayFormatted.isEmpty()) {
					// Parse string of date to date
					Calendar playerBirthdayCalendar = Calendar.getInstance();
					playerBirthdayCalendar.setTime(dateFormatter.parse(birthdayFormatted));
					currentPlayer.setBirthdate(playerBirthdayCalendar);
				}
				
				// Get country by name -> if there is no country yet, create one
				try {
					currentPlayer.getAddress().setCountry(countryService.findCountry(currentPlayer.getAddress().getCountry().getName()));
				} catch (CountryNotFounException e1) {
					try {
						Country newCountry = new Country(currentPlayer.getAddress().getCountry().getName());
						currentPlayer.getAddress().setCountry(countryService.createCountry(newCountry));
					} catch (CountryAlreadyExistsException e) {
						// TODO: Initialize all country's on DB creation
					}
				}
				// Update current player and reset the old email address to the persisted one (used to check for duplicate emails)
				currentPlayer = playerService.update(currentPlayer);
				oldEmailAddress = currentPlayer.getEmail();
			} catch (ParseException e) {
				FacesContext facesContext = FacesContext.getCurrentInstance();
				// TODO: localization
				FacesMessage facesMessage = new FacesMessage("Invalid date format", "Date format must be in dd.MM.yyyy!");
				facesContext.addMessage("updatePlayerForm:datePickerPlayerBirthday", facesMessage);
			}
		}
	}
	
	private boolean checkForDuplicateEmail() {
		// Check if user has a new email and if that email is already occupied
		if(!oldEmailAddress.equals(currentPlayer.getEmail())) {
			try {
				playerService.findPlayer(currentPlayer.getEmail());
				FacesContext facesContext = FacesContext.getCurrentInstance();
				// TODO: localization
				FacesMessage facesMessage = new FacesMessage("Email already exists", "Email already exists!");
				facesContext.addMessage("updatePlayerForm:player_email", facesMessage);
				return false;
			} catch (PlayerNotFoundException e) {
				// Email is free
				return true;
			}
		}
		return true;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}
	
	public Position[] getPositions() {
		return Position.values();
	}

	public String getBirthdayFormatted() {
		return birthdayFormatted;
	}

	public void setBirthdayFormatted(String birthdayFormatted) {
		this.birthdayFormatted = birthdayFormatted;
	}

}
