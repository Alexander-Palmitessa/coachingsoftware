package org.example;

import java.io.Serializable;
import java.util.HashSet;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.ClubAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.PlayerAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.PlayerServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
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
	@EJB
	private PlayerServiceRemote playerService;
	
	public String doLogin() {
		try {
			currentUser = userService.findUser(username);
			if(currentUser.getPassword().equals(password)) {
				loggedIn = true;
				
				try {
					Player player1 = playerService.createPlayer(new Player("Elias","Schildknecht","test@test.ch"));
					Player player2 = playerService.createPlayer(new Player("Alexander","Palmitessa","test@test2.ch"));
					Club club = teamClubService.createClub(new Club("Verein 1"));
					Team team = teamClubService.createTeam(new Team("Team 1",club));
					HashSet<Player> players = new HashSet<Player>();
					players.add(player1);
					players.add(player2);
					team.setPlayers(players);
					club.addTeam(team);
					currentUser.setTeam(team);
					userService.updateUser(currentUser);
				} catch (PlayerAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (TeamAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (ClubAlreadyExistsException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				
				if(currentUser.getTeam() != null) {
					hasUserAssignedTeam = true;
					return navigationBean.redirectToPlayerOverview();
				} else {
					return navigationBean.redirectToUserSettings();
				}
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
