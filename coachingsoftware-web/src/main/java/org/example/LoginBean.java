package org.example;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

@Named(value = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {

	private static final long serialVersionUID = 3548028163173684077L;
	
	private String username;
	private String password;
	
	private boolean loggedIn;
	
	@Inject
	private NavigationBean navigationBean;
	
	@EJB
	private UserServiceRemote userService;
	
	public String doLogin() {
		try {
			UserAccount currentUserAccount = userService.findUser(username);
			if(currentUserAccount.getPassword().equals(password)) {
				loggedIn = true;
				return navigationBean.redirectToPlayerOverview();
			}
		} catch (UserNotFoundException e) {
			loggedIn = false;
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

}
