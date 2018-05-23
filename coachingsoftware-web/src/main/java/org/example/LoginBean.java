package org.example;

import java.io.Serializable;
import java.util.HashSet;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.*;
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
	private String userTeam;
	
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
		try {
			userService.createUser(new UserAccount("elias","elias","elias.schildknecht@students.bfh.ch"));
			indexBean.init();
		} catch (UserAlreadyExistsException e) {
			// TODO 
		} catch (ClubAlreadyExistsException | ArenaAlreadyExistsException | TeamAlreadyExistsException |
				PlayerAlreadyExistsException | GameAlreadyExistsException e) {
			e.printStackTrace();
		}
	}
	
	public String doLogin() {
		try {
			UserAccount currentUser = userService.findUser(username);
			if(userService.authenticate(password, currentUser.getPassword())) {
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
					teamClubService.updateTeam(team);
					club.addTeam(team);
					teamClubService.updateClub(club);
					currentUser.setTeam(team);
					userService.updateUser(currentUser);
					userTeam = team.getName();
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

	public String getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}

}
