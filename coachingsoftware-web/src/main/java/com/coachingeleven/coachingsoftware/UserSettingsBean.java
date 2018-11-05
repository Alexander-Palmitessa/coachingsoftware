package com.coachingeleven.coachingsoftware;

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
    @Inject
	private NavigationBean navigationBean;
    
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
    
    // TODO: In further requirements
    public String persistUserTeamSeason(String teamname, int seasonID) {
    	UserAccount currentUser = loginBean.getLoggedInUser();
		try {
			Team team = teamClubService.findTeam(teamname);
			currentUser.setTeam(team);
		} catch (TeamNotFoundException e) {
			// TODO Auto-generated catch block
		}
    	
    	loginBean.setHasUserAssignedTeam(true);
		userService.updateUser(currentUser);
    	return navigationBean.toTeamDataOverview();
    }
    
    public String persistUserTeam() {
    	try {
			Team team = teamClubService.findTeam(selectedTeamID);
			if(team != null) {
				UserAccount currentUser = loginBean.getLoggedInUser();
				currentUser.setTeam(team);
				loginBean.setHasUserAssignedTeam(true);
				loginBean.setUserTeam(team.getName());
				loginBean.setUserTeamID(team.getID());
				userService.updateUser(currentUser);
				return navigationBean.redirectToTeamDataOverview();
			}
		} catch (TeamNotFoundException e) {
			// TODO
		}
    	return navigationBean.redirectToUserSettings();
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
