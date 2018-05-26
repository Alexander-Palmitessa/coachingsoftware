package com.coachingeleven.coachingsoftware;

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
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;

@Named(value = "playerBean")
@RequestScoped
public class PlayerBean {
	
	@Inject
	private LoginBean loginBean;
	@Inject
	private NavigationBean navigationBean;
	@EJB
	private UserServiceRemote userService;
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
    private CountryServiceRemote countryService;
	
	private List<Player> currentPlayers;
	private List<Player> historyPlayers;
	
	private Player showPlayer;
	private Player newPlayer;
	private Address newPlayerAddress;
	private Country newPlayerCountry;
	
	@PostConstruct
    public void init() {
		newPlayer = new Player();
		newPlayerAddress = new Address();
		newPlayerCountry = new Country();
		currentPlayers = playerService.findCurrentPlayersByTeam(loginBean.getLoggedInUser().getTeam().getID());
		historyPlayers = playerService.findHistoryPlayersByTeam(loginBean.getLoggedInUser().getTeam().getID());
    }
	
	public String createPlayer() throws CountryAlreadyExistsException, PlayerNotFoundException {
		try {
			newPlayerCountry = countryService.findCountry(newPlayerCountry.getName());
		} catch (CountryNotFounException e1) {
			newPlayerCountry = countryService.createCountry(newPlayerCountry);
		}
		
		newPlayerAddress.setCountry(newPlayerCountry);
		newPlayer.setAddress(newPlayerAddress);
		// Add created player to the managed team of the logged in user
		newPlayer.setCurrentTeam(loginBean.getLoggedInUser().getTeam());
		
		try {
			newPlayer = playerService.createPlayer(newPlayer);
		} catch (PlayerAlreadyExistsException e) {
			newPlayer = playerService.findPlayer(newPlayer.getID());
		}
		
		return navigationBean.toPlayerForm();
	}
	
	public String showPlayer(int playerId) {
		try {
			showPlayer = playerService.findPlayer(playerId);
			// TODO: Reload player content via ajax
			return navigationBean.toPlayer();
		} catch (PlayerNotFoundException e) {
			return navigationBean.toPlayerOverview();
		}
	}
	
	public Position[] getPositions() {
		return Position.values();
	}

	public Player getShowPlayer() {
		return showPlayer;
	}

	public void setShowPlayer(Player showPlayer) {
		this.showPlayer = showPlayer;
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

	public List<Player> getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(List<Player> currentPlayers) {
		this.currentPlayers = currentPlayers;
	}

	public List<Player> getHistoryPlayers() {
		return historyPlayers;
	}

	public void setHistoryPlayers(List<Player> historyPlayers) {
		this.historyPlayers = historyPlayers;
	}
}
