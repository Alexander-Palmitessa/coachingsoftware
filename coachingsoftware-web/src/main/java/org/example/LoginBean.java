package org.example;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3548028163173684077L;
	
	private String username;
	private String password;
	
	private boolean loggedIn;
	
	private UserAccount currentUser;
	
	private boolean hasUserAssignedTeam;
	
	@Inject
	private NavigationBean navigationBean;
	
	@EJB
	private UserServiceRemote userService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	
	public String doLogin() {
		try {
			currentUser = userService.findUser(username);
			if(currentUser.getPassword().equals(password)) {
				loggedIn = true;
				if(currentUser.getTeam() != null) hasUserAssignedTeam = true;
				return navigationBean.redirectToPlayerOverview();
			}
		} catch (UserNotFoundException e) {
			loggedIn = false;
		}
		
		return navigationBean.redirectToLogin();
	}
	
	public void updateUser() {
		if(currentUser != null) {
			userService.updateUser(currentUser);
		}
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

	public UserAccount getCurrentUser() {
		return currentUser;
	}

	public void setCurrentUser(UserAccount currentUser) {
		this.currentUser = currentUser;
	}

	public boolean isHasUserAssignedTeam() {
		return hasUserAssignedTeam;
	}

	public void setHasUserAssignedTeam(boolean hasUserAssignedTeam) {
		this.hasUserAssignedTeam = hasUserAssignedTeam;
	}

}
