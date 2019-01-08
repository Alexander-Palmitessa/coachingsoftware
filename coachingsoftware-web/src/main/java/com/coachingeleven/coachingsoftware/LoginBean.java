package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.ContactService;
import com.coachingeleven.coachingsoftware.application.service.CountryServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.entity.CountryBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;
import com.coachingeleven.coachingsoftware.persistence.enumeration.AccountRole;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3548028163173684077L;

	private static final Logger logger = Logger.getLogger(LoginBean.class.getName());
	
	private String username;
	private String password;
	private UserAccount loggedInUser;
	private Team loggedInUserTeam;
	private Season loggedInUserSeason;
	private boolean loggedIn;

	@Inject
	private NavigationBean navigationBean;
	@Inject
	private CountryBean countryBean;

	@EJB
	private UserServiceRemote userService;
	@EJB
	private ContactService contactService;
	@EJB
	private CountryServiceRemote countryService;
	@EJB
	private TeamContactServiceRemote teamContactService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private SeasonServiceRemote seasonService;
	
	private String userBirthdayFormatted;

	@PostConstruct
    public void init() {
		try {
			userService.findUser("elias");
		} catch (UserNotFoundException e) {
			try {
				logger.info("Creating default user account elias - elias");
				countryBean.init();
				loggedInUser = new UserAccount("elias","elias");
				loggedInUser.setAccountRole(AccountRole.ADMINISTRATOR);
				Contact contact = new Contact("Elias","Schildknecht");
				contact.setRole(Role.TRAINER);
				Address address = new Address();
				Country country = new Country();
				country.setID(1);
				address.setCountry(country);
				contact.setAddress(address);
				loggedInUser.setContact(contact);
				userService.createUser(loggedInUser);
			} catch (UserAlreadyExistsException e1) {
				logger.warning("Could not create user account: " + e1.getMessage());
			}
		}
	}

	public String doLogin() {
		try {
			UserAccount currentUser = userService.findUser(username);
			if(userService.authenticate(password, currentUser.getPassword())) {
				loggedIn = true;
				loggedInUser = currentUser;
				return navigationBean.redirectToUserSettings();
			}
		} catch (UserNotFoundException e) {
			logger.log(Level.INFO, e.getMessage());
		}
		return navigationBean.redirectToLogin();
	}

	public String doLogout() {
		loggedIn = false;
		loggedInUserTeam = null;
		loggedInUserSeason = null;
		return navigationBean.toLogin();
	}
	
	public String assignCurrentTeam(Team team) {
		if(loggedInUser != null && team != null) {
			try {
				loggedInUserTeam = teamClubService.findTeam(team.getID());
				List<Season> seasons = seasonService.findSeasonsForAssignedTeam(team.getID(), loggedInUser.getContact().getID());
				if(seasons.size() > 0) loggedInUserSeason = seasons.get(0);
				return navigationBean.redirectToTeamDataOverview();
			} catch (TeamNotFoundException e) {
				logger.warning("Could not find team with ID (" + team.getID() + "): " + e.getMessage());
			}
		}
		return navigationBean.redirectToAssignCurrentTeam();
	}
	
	public String unassignCurrentTeam() {
		loggedInUserTeam = null;
		loggedInUserSeason = null;
		return navigationBean.redirectToAssignCurrentTeam();
	}
	
	public void pullLoggedInUserSeason(int seasonID) {
		logger.warning("SEASON ID: " + seasonID);
		try {
			loggedInUserSeason = seasonService.findSeason(seasonID);
		} catch (SeasonNotFoundException e) {
			logger.warning("Could not find season with ID (" + seasonID + "): " + e.getMessage());
		}
	}

	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserAccount getLoggedInUser() {
		return loggedInUser;
	}

	public void setLoggedInUser(UserAccount loggedInUser) {
		this.loggedInUser = loggedInUser;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public Team getLoggedInUserTeam() {
		return loggedInUserTeam;
	}

	public void setLoggedInUserTeam(Team loggedInUserTeam) {
		this.loggedInUserTeam = loggedInUserTeam;
	}

	public Season getLoggedInUserSeason() {
		return loggedInUserSeason;
	}

	public void setLoggedInUserSeason(Season loggedInUserSeason) {
		this.loggedInUserSeason = loggedInUserSeason;
	}

	public String getUserBirthdayFormatted() {
		return userBirthdayFormatted;
	}

	public void setUserBirthdayFormatted(String userBirthdayFormatted) {
		this.userBirthdayFormatted = userBirthdayFormatted;
	}
}
