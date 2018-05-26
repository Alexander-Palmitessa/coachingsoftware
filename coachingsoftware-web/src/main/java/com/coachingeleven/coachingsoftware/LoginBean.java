package com.coachingeleven.coachingsoftware;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.*;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3548028163173684077L;
	
	private String username;
	private String password;
	
	private UserAccount loggedInUser;
	
	private boolean loggedIn;
	
	private boolean hasUserAssignedTeam;
	
	@Inject
	private NavigationBean navigationBean;
	
	@EJB
	private UserServiceRemote userService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	@EJB
	private PlayerServiceRemote playerService;

	@Inject
	private IndexBean indexBean;
	
	@PostConstruct
    public void init() {
		if(loggedInUser == null) loggedInUser = new UserAccount();
		try {
			loggedInUser = userService.createUser(new UserAccount("elias","elias","elias.schildknecht@students.bfh.ch"));
			indexBean.init();
		} catch (UserAlreadyExistsException | ArenaAlreadyExistsException | ClubAlreadyExistsException | TeamAlreadyExistsException | PlayerAlreadyExistsException | GameAlreadyExistsException e) {
			// TODO 
		}
		
	}
	
	public String doLogin() {
		if(userService.authenticate(password, loggedInUser.getPassword())) {
			loggedIn = true;
			if(loggedInUser.getTeam() != null) {
				hasUserAssignedTeam = true;
				return navigationBean.redirectToHome();
			} else {
				return navigationBean.redirectToUserSettings();
			}
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

}
