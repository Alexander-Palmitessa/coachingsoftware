package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.util.TotalPlayerStats;
import com.coachingeleven.coachingsoftware.util.ZoneCountPlayer;

@Named(value = "playerViewBean")
@RequestScoped
public class PlayerViewBean {
	
	private static final Logger logger = Logger.getLogger(PlayerViewBean.class.getName());

	@Inject
	private CurrentPlayerBean currentPlayerBean;
	@Inject
	private LoginBean loginBean;

	@EJB
	private PlayerServiceRemote playerService;
	@EJB
	private CountryServiceRemote countryService;
	@EJB
	private StatisticsServiceRemote statisticsService;
	@EJB
	private ContactServiceRemote contactService;

	private Player currentPlayer;

	private SimpleDateFormat dateFormatter;

	private String birthdayFormatted;
	private String oldEmailAddress;

	private TotalPlayerStats totalPlayerStats;
	private ZoneCountPlayer goalsZones;
	private ZoneCountPlayer assistZones;


	@PostConstruct
	public void init() {
		currentPlayer = currentPlayerBean.getCurrentPlayer();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		Season selectedSeason = loginBean.getLoggedInUserSeason();
		if(statisticsService.getNumberOfGames(selectedSeason, currentPlayer.getID()) > 0) {
			totalPlayerStats = new TotalPlayerStats(currentPlayer, statisticsService, selectedSeason);
			goalsZones = new ZoneCountPlayer(statisticsService, selectedSeason, currentPlayer);
			assistZones = new ZoneCountPlayer(statisticsService, selectedSeason, currentPlayer);
		}
		// Format date for GUI
		if(currentPlayer.getContact().getBirthdate() != null) birthdayFormatted = dateFormatter.format(currentPlayer.getContact().getBirthdate().getTime());
		// Create empty address if player doesn't have one
		if(currentPlayer.getContact().getAddress() == null) currentPlayer.getContact().setAddress(new Address());
		// Create new country if player doesn't have one assigned
		if(currentPlayer.getContact().getAddress().getCountry() == null) currentPlayer.getContact().getAddress().setCountry(new Country());
		oldEmailAddress = currentPlayer.getContact() != null ? currentPlayer.getContact().getEmail() : "";
	}

	public void updateCurrentPlayer() {
		logger.warning("OLDEMAILADDRESS: " + oldEmailAddress);
		logger.warning("EMAILADDRESS: " + currentPlayer.getContact().getEmail());
		if (checkForDuplicateEmail()) {
			try {
				// Set new birthday for current player
				if(birthdayFormatted != null && !birthdayFormatted.isEmpty()) {
					// Parse string of date to date
					Calendar playerBirthdayCalendar = Calendar.getInstance();
					playerBirthdayCalendar.setTime(dateFormatter.parse(birthdayFormatted));
					currentPlayer.getContact().setBirthdate(playerBirthdayCalendar);
				}
				// Update current player and reset the old email address to the persisted one (used to check for duplicate emails)
				contactService.update(currentPlayer.getContact());
				currentPlayer = playerService.update(currentPlayer);
				oldEmailAddress = currentPlayer.getContact().getEmail();
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
		if(!oldEmailAddress.equals(currentPlayer.getContact().getEmail())) {
			try {
				contactService.findContact(currentPlayer.getContact().getEmail());
				FacesContext facesContext = FacesContext.getCurrentInstance();
				// TODO: localization
				FacesMessage facesMessage = new FacesMessage("Email already exists", "Email already exists!");
				facesContext.addMessage("updatePlayerForm:player_email", facesMessage);
				return false;
			} catch (ContactNotFoundException e) {
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

	public TotalPlayerStats getTotalPlayerStats() {
		return totalPlayerStats;
	}

	public void setTotalPlayerStats(TotalPlayerStats totalPlayerStats) {
		this.totalPlayerStats = totalPlayerStats;
	}

	public ZoneCountPlayer getGoalsZones() {
		return goalsZones;
	}

	public void setGoalsZones(ZoneCountPlayer goalsZones) {
		this.goalsZones = goalsZones;
	}

	public ZoneCountPlayer getAssistZones() {
		return assistZones;
	}

	public void setAssistZones(ZoneCountPlayer assistZones) {
		this.assistZones = assistZones;
	}
}
