package org.example;

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
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

@Named("userSettingsBean")
@RequestScoped
public class UserSettingsBean {

	@EJB
    private TeamClubServiceRemote teamClubService;
	@EJB
	private UserServiceRemote userService;
	
	private List<Team> teams;
    private int selectedTeamID;
    
    @Inject
	private LoginBean loginBean;
    
    @PostConstruct
    public void init() {
        teams = teamClubService.findAllTeams();
		try {
			UserAccount currentUser = userService.findUser(loginBean.getUsername());
			if(currentUser.getTeam() != null) {
	        	selectedTeamID = currentUser.getTeam().getID();
	        }
		} catch (UserNotFoundException e) {
			// TODO
		}
    }
    
    public void persistUserTeam() {
    	try {
			Team team = teamClubService.findTeam(selectedTeamID);
			if(team != null) {
				try {
					UserAccount currentUser = userService.findUser(loginBean.getUsername());
					currentUser.setTeam(team);
					loginBean.setUserTeam(team.getName());
					loginBean.setHasUserAssignedTeam(true);
					userService.updateUser(currentUser);
				} catch (UserNotFoundException e) {
					// TODO
				}
			}
		} catch (TeamNotFoundException e) {
			// TODO
		}
    }
    
    public List<Team> getTeams() {
		return teams;
	}

	public void setTeams(List<Team> teams) {
		this.teams = teams;
	}

	public int getSelectedTeamID() {
		return selectedTeamID;
	}

	public void setSelectedTeamID(int selectedTeamID) {
		this.selectedTeamID = selectedTeamID;
	}
	
}
