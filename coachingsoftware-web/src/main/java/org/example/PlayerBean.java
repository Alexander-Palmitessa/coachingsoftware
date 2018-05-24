package org.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;

@Named(value = "playerBean")
@RequestScoped
public class PlayerBean {
	
	@Inject
	private LoginBean loginBean;
	@EJB
	private UserServiceRemote userService;
	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private CountryServiceRemote countryService;
	
	private List<Player> playersOfCurrentUser;
	
	private String playerFirstName;
	private String playerLastName;
	private String playerEmail;
	private String playerMobileNumber;
	private String playerCity;
	private String playerStreet;
	private String playerStreetNr;
	private int playerZip;
	private String playerCountry;
	private Position position;
	
	@PostConstruct
    public void init() {
		playersOfCurrentUser = new ArrayList<Player>();
		try {
			UserAccount currentUser = userService.findUser(loginBean.getUsername());
			Team team = teamClubService.findTeam(currentUser.getTeam().getID());
			playersOfCurrentUser.addAll(team.getPlayers());
		} catch (UserNotFoundException e) {
			// TODO 
		} catch (TeamNotFoundException e) {
			// TODO 
		}
    }
	
	public void createPlayer() {
		Country selectedCountry = null;
		try {
			selectedCountry = countryService.createCountry(new Country(playerCountry));
		} catch (CountryAlreadyExistsException e1) {
			try {
				selectedCountry = countryService.findCountry(playerCountry);
			} catch (CountryNotFounException e) {
				// TODO 
			}
		}
		Address address = new Address(playerCity,playerStreet,playerStreetNr,playerZip,selectedCountry);
		Player newPlayer = new Player(playerFirstName,playerLastName,playerEmail,playerMobileNumber,address,position);
		
		// Add new player to the current team of the logged in user
		try {
			UserAccount currentUser = userService.findUser(loginBean.getUsername());
			Team team = teamClubService.findTeam(currentUser.getTeam().getID());
			team.getPlayers().add(newPlayer);
			teamClubService.updateTeam(team);
			playersOfCurrentUser.add(newPlayer);
		} catch (UserNotFoundException e) {
			// TODO 
		} catch (TeamNotFoundException e) {
			// TODO 
		}
	}

	public List<Player> getPlayersOfCurrentUser() {
		return playersOfCurrentUser;
	}

	public String getPlayerFirstName() {
		return playerFirstName;
	}

	public void setPlayerFirstName(String playerFirstName) {
		this.playerFirstName = playerFirstName;
	}

	public String getPlayerLastName() {
		return playerLastName;
	}

	public void setPlayerLastName(String playerLastName) {
		this.playerLastName = playerLastName;
	}

	public String getPlayerEmail() {
		return playerEmail;
	}

	public void setPlayerEmail(String playerEmail) {
		this.playerEmail = playerEmail;
	}

	public String getPlayerMobileNumber() {
		return playerMobileNumber;
	}

	public void setPlayerMobileNumber(String playerMobileNumber) {
		this.playerMobileNumber = playerMobileNumber;
	}

	public PlayerServiceRemote getPlayerService() {
		return playerService;
	}

	public void setPlayerService(PlayerServiceRemote playerService) {
		this.playerService = playerService;
	}

	public String getPlayerCity() {
		return playerCity;
	}

	public void setPlayerCity(String playerCity) {
		this.playerCity = playerCity;
	}

	public String getPlayerStreet() {
		return playerStreet;
	}

	public void setPlayerStreet(String playerStreet) {
		this.playerStreet = playerStreet;
	}

	public String getPlayerStreetNr() {
		return playerStreetNr;
	}

	public void setPlayerStreetNr(String playerStreetNr) {
		this.playerStreetNr = playerStreetNr;
	}

	public int getPlayerZip() {
		return playerZip;
	}

	public void setPlayerZip(int playerZip) {
		this.playerZip = playerZip;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public void setPlayersOfCurrentUser(List<Player> playersOfCurrentUser) {
		this.playersOfCurrentUser = playersOfCurrentUser;
	}

	public Position[] getPositions() {
		return Position.values();
	}

	public String getPlayerCountry() {
		return playerCountry;
	}

	public void setPlayerCountry(String playerCountry) {
		this.playerCountry = playerCountry;
	}
}
