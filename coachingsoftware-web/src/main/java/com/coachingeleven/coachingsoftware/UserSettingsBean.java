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
    	List<Team> seasonTeams = teamClubService.findTeamsBySeasonID(seasonID);
    	UserAccount currentUser = loginBean.getLoggedInUser();
    	for(Team team : seasonTeams) {
    		if(team.getName().equals(teamname)) {
    			if(currentUser.getTeam() == null || currentUser.getTeam().getID() != team.getID()) {
    				currentUser.setTeam(team);
    			}
    		}
    	}
    	loginBean.setHasUserAssignedTeam(true);
		userService.updateUser(currentUser);
    	return navigationBean.toTeamDataOverview();
    }
    
    public void persistUserTeam() {
    	try {
			Team team = teamClubService.findTeam(selectedTeamID);
			if(team != null) {
				UserAccount currentUser = loginBean.getLoggedInUser();
				currentUser.setTeam(team);
				loginBean.setHasUserAssignedTeam(true);
				userService.updateUser(currentUser);
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
