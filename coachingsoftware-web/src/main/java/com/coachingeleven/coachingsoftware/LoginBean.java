package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.text.SimpleDateFormat;
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
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.entity.CountryBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Address;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Country;
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
	private boolean loggedIn;

	private boolean hasUserAssignedTeam;

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
	
	private String userBirthdayFormatted;
	private SimpleDateFormat dateFormatter;

	@PostConstruct
    public void init() {
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
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
				try {
					loggedInUserTeam = contactService.findAssignedTeam(currentUser.getContact());
					if(loggedInUser.getContact().getBirthdate() != null) userBirthdayFormatted = dateFormatter.format(loggedInUser.getContact().getBirthdate().getTime());
					hasUserAssignedTeam = true;
					return navigationBean.redirectToHome();
				} catch (NoTeamAssignedException e) {
					return navigationBean.redirectToUserSettings();
				}
			}
		} catch (UserNotFoundException e) {
			// TODO Auto-generated catch block
			logger.log(Level.INFO, e.getMessage());
		}
		return navigationBean.redirectToLogin();
	}

	public String doLogout() {
		loggedIn = false;
		return navigationBean.toLogin();
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

	public boolean isHasUserAssignedTeam() {
		return hasUserAssignedTeam;
	}

	public void setHasUserAssignedTeam(boolean hasUserAssignedTeam) {
		this.hasUserAssignedTeam = hasUserAssignedTeam;
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

	public String getUserBirthdayFormatted() {
		return userBirthdayFormatted;
	}

	public void setUserBirthdayFormatted(String userBirthdayFormatted) {
		this.userBirthdayFormatted = userBirthdayFormatted;
	}
}
