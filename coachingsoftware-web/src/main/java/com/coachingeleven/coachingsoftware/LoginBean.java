package com.coachingeleven.coachingsoftware;

import java.io.Serializable;
import java.text.ParseException;
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
	private UserAccount loggedInUser;
	private String userTeam;
	private int userTeamID;
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
		} catch (UserAlreadyExistsException | ArenaAlreadyExistsException | ClubAlreadyExistsException | TeamAlreadyExistsException | PlayerAlreadyExistsException | GameAlreadyExistsException | CountryAlreadyExistsException | SeasonAlreadyExistsException | ParseException e) {
			// TODO 
		}
		
	}

	public String doLogin() {
		try {
			UserAccount currentUser = userService.findUser(username);
			if(userService.authenticate(password, currentUser.getPassword())) {
				loggedIn = true;

				try {
					Player player1 = playerService.createPlayer(new Player("Elias","Schildknecht","test@test.ch"));
					player1.setAvatarUrl("images/Default-avatar.jpg");
					playerService.update(player1);
					Player player2 = playerService.createPlayer(new Player("Alexander","Palmitessa","test@test2.ch"));
					player2.setAvatarUrl("images/Default-avatar.jpg");
					playerService.update(player2);
					Club club = teamClubService.createClub(new Club("Verein 1"));
					Team team = teamClubService.createTeam(new Team("Team 1",club));
					team.setTeamPictureURL("images/sfv_u19.jpg");
					HashSet<Player> players = new HashSet<Player>();
					players.add(player1);
					players.add(player2);
					team.setPlayers(players);
					team.setCurrentPlayers(players);
					teamClubService.updateTeam(team);
					club.addTeam(team);
					teamClubService.updateClub(club);
					userService.updateUser(currentUser);
					userTeam = team.getName();
					userTeamID = team.getID();
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
					return navigationBean.redirectToHome();
				} else {
					return navigationBean.redirectToUserSettings();
				}
			}
		} catch (UserNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
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

	public String getUserTeam() {
		return userTeam;
	}

	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}

	public int getUserTeamID() {
		return userTeamID;
	}

	public void setUserTeamID(int userTeamID) {
		this.userTeamID = userTeamID;
	}
}
