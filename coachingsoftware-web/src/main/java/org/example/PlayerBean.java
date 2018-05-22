package org.example;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

@Named(value = "playerBean")
@RequestScoped
public class PlayerBean {
	
	@Inject
	private LoginBean loginBean;
	@EJB
	private UserServiceRemote userService;
	@EJB
	private TeamClubServiceRemote teamClubService;
	
	private List<Player> userTeamPlayers;
	
	@PostConstruct
    public void init() {
		userTeamPlayers = new ArrayList<Player>();
		try {
			UserAccount currentUser = userService.findUser(loginBean.getUsername());
			Team team = teamClubService.findTeam(currentUser.getTeam().getID());
			userTeamPlayers.addAll(team.getPlayers());
		} catch (UserNotFoundException e) {
			// TODO 
		} catch (TeamNotFoundException e) {
			// TODO 
		}
    }

	public List<Player> getUserTeamPlayers() {
		return userTeamPlayers;
	}
}
