package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

@Named(value = "playerBean")
@RequestScoped
public class PlayerBean implements Serializable {

	private static final long serialVersionUID = 362437179842480504L;
	
	@Inject
	private NavigationBean navigationBean;
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private CountryServiceRemote countryService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	
	private List<Player> allPlayers;

	private Player newPlayer;
	private Contact newPlayerContact;
	private Address newPlayerAddress;
	private Country newPlayerCountry;
	private Country newPlayerCountryPermission;

	private String playerBirthday;

	private SimpleDateFormat dateFormatter;

	@PostConstruct
	public void init() {
		newPlayer = new Player();
		newPlayerContact = new Contact();
		newPlayerAddress = new Address();
		newPlayerCountry = new Country();
		newPlayerCountryPermission = new Country();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		allPlayers = playerService.findAllPlayers();
	}

	public String createPlayer() throws CountryAlreadyExistsException, PlayerNotFoundException {
		try {
			newPlayerCountry = countryService.findCountry(newPlayerCountry.getName());
			newPlayerCountryPermission = countryService.findCountry(newPlayerCountryPermission.getName());
		} catch (CountryNotFounException e1) {
			newPlayerCountry = countryService.createCountry(newPlayerCountry);
		}
		try {
			newPlayerCountryPermission = countryService.findCountry(newPlayerCountryPermission.getName());
		} catch (CountryNotFounException e1) {
			newPlayerCountryPermission = countryService.createCountry(newPlayerCountryPermission);
		}
		newPlayer.setCountryPermission(newPlayerCountryPermission);
		newPlayerAddress.setCountry(newPlayerCountry);
		newPlayerContact.setAddress(newPlayerAddress);

		if(playerBirthday != null) {
			try {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(dateFormatter.parse(playerBirthday));
				newPlayerContact.setBirthdate(calendar);
			} catch (ParseException e1) {
				// TODO 
			}
		}
		
		newPlayerContact.setRole(Role.PLAYER);
		newPlayer.setContact(newPlayerContact);

		try {
			newPlayer = playerService.createPlayer(newPlayer);
		} catch (PlayerAlreadyExistsException e) {
			newPlayer = playerService.findPlayer(newPlayer.getID());
		}

		return navigationBean.redirectToCurrentPlayersOverview();
	}

	public Player getNewPlayer() {
		return newPlayer;
	}

	public void setNewPlayer(Player newPlayer) {
		this.newPlayer = newPlayer;
	}

	public Address getNewPlayerAddress() {
		return newPlayerAddress;
	}

	public void setNewPlayerAddress(Address newPlayerAddress) {
		this.newPlayerAddress = newPlayerAddress;
	}

	public Country getNewPlayerCountry() {
		return newPlayerCountry;
	}

	public void setNewPlayerCountry(Country newPlayerCountry) {
		this.newPlayerCountry = newPlayerCountry;
	}

	public String getPlayerBirthday() {
		return playerBirthday;
	}

	public void setPlayerBirthday(String playerBirthday) {
		this.playerBirthday = playerBirthday;
	}
	
	public Position[] getPositions() {
		return Position.values();
	}

	public Country getNewPlayerCountryPermission() {
		return newPlayerCountryPermission;
	}

	public void setNewPlayerCountryPermission(Country newPlayerCountryPermission) {
		this.newPlayerCountryPermission = newPlayerCountryPermission;
	}

	public List<Player> getAllPlayers() {
		return allPlayers;
	}

	public void setAllPlayers(List<Player> allPlayers) {
		this.allPlayers = allPlayers;
	}

	public Contact getNewPlayerContact() {
		return newPlayerContact;
	}

	public void setNewPlayerContact(Contact newPlayerContact) {
		this.newPlayerContact = newPlayerContact;
	}
}
