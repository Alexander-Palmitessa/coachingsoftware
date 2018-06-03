package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;

import com.coachingeleven.coachingsoftware.application.exception.CountryAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.CountryNotFounException;
import com.coachingeleven.coachingsoftware.application.exception.EvaluationTalkAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerEvaluationServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
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
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private PlayerEvaluationServiceRemote evaluationTalkService;
	
	private List<Player> currentPlayers;
	private List<Player> historyPlayers;
	
	private Player currentPlayer;
	private Player newPlayer;
	private Address newPlayerAddress;
	private Country newPlayerCountry;
	
	private Integer playerID;
	
	private String playerBirthday;
	
	private SimpleDateFormat dateFormatter;
	
	private EvaluationTalk newTalk;
	private String newTalkDate;
	
	@PostConstruct
    public void init() {
		newTalk = new EvaluationTalk();
		newPlayer = new Player();
		newPlayerAddress = new Address();
		newPlayerCountry = new Country();
		currentPlayers = playerService.findCurrentPlayersByTeam(loginBean.getLoggedInUser().getTeam().getID());
		historyPlayers = playerService.findHistoryPlayersByTeam(loginBean.getLoggedInUser().getTeam().getID());
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    }
	
	public void setRequestParameters() {
		if(playerID != null) {
			try {
				HttpServletRequest request = (HttpServletRequest) FacesContext.getCurrentInstance().getExternalContext().getRequest();
				if(request.getParameter("playerID") != null) {
					playerID = Integer.parseInt(request.getParameter("playerID"));
					currentPlayer = playerService.findPlayer(playerID);
				}
			} catch (PlayerNotFoundException e) {
				// TODO
			}
		}
	}
	
	public String createPlayer() throws CountryAlreadyExistsException, PlayerNotFoundException {
		try {
			newPlayerCountry = countryService.findCountry(newPlayerCountry.getName());
		} catch (CountryNotFounException e1) {
			newPlayerCountry = countryService.createCountry(newPlayerCountry);
		}
		
		newPlayerAddress.setCountry(newPlayerCountry);
		newPlayer.setAddress(newPlayerAddress);
		
		try {
			Calendar playerBirthdayCalendar = Calendar.getInstance();
			playerBirthdayCalendar.setTime(dateFormatter.parse(playerBirthday));
			newPlayer.setBirthdate(playerBirthdayCalendar);
		} catch (ParseException e1) {
			// TODO 
		}
		
		try {
			newPlayer = playerService.createPlayer(newPlayer);
		} catch (PlayerAlreadyExistsException e) {
			newPlayer = playerService.findPlayer(newPlayer.getID());
		}
		
		// Add created player to the managed team of the logged in user
		try {
			Team team = teamClubService.findTeam(loginBean.getLoggedInUser().getTeam().getID());
			teamClubService.addPlayerToTeam(team.getID(), newPlayer);
		} catch (TeamNotFoundException e) {
			// TODO 
		}
		
		return navigationBean.redirectToCurrentPlayersOverview();
	}
	
	public void createPlayerTalk() {
		if(currentPlayer != null) {
			try {
				Calendar startDateCalendar = Calendar.getInstance();
				startDateCalendar.setTime(dateFormatter.parse(newTalkDate));
				newTalk.setPlayer(currentPlayer);
				evaluationTalkService.createEvaluationTalk(newTalk);
			} catch (ParseException e) {
				// TODO 
			} catch (EvaluationTalkAlreadyExistsException e) {
				// TODO 
			}
		}
	}
	
	public Position[] getPositions() {
		return Position.values();
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

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public void setCurrentPlayer(Player currentPlayer) {
		this.currentPlayer = currentPlayer;
	}

	public Integer getPlayerID() {
		return playerID;
	}

	public void setPlayerID(Integer playerID) {
		this.playerID = playerID;
	}

	public String getPlayerBirthday() {
		return playerBirthday;
	}

	public void setPlayerBirthday(String playerBirthday) {
		this.playerBirthday = playerBirthday;
	}

	public String getNewTalkDate() {
		return newTalkDate;
	}

	public void setNewTalkDate(String newTalkDate) {
		this.newTalkDate = newTalkDate;
	}

	public EvaluationTalk getNewTalk() {
		return newTalk;
	}

	public void setNewTalk(EvaluationTalk newTalk) {
		this.newTalk = newTalk;
	}
}
