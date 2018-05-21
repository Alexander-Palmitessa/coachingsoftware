package org.example;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.UserServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

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
	private PlayerBean playerBean;
    
    @PostConstruct
    public void init() {
        teams = teamClubService.findAllTeams();
        if(loginBean.getCurrentUser().getTeam() != null) {
        	selectedTeamID = loginBean.getCurrentUser().getTeam().getID();
        }
    }
    
    public void persistUserTeam() {
    	try {
			Team team = teamClubService.findTeam(selectedTeamID);
			if(team != null) {
				loginBean.getCurrentUser().setTeam(team);
				loginBean.setHasUserAssignedTeam(true);
				loginBean.updateUser();
			}
		} catch (TeamNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
