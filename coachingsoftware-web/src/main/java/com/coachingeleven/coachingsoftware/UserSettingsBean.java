package com.coachingeleven.coachingsoftware;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactService;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

@Named("userSettingsBean")
@RequestScoped
public class UserSettingsBean {

	@EJB
    private TeamClubServiceRemote teamClubService;
	@EJB
	private ContactService contactService;
	@EJB
	private TeamContactServiceRemote teamContactService;
	
	private List<Team> teams;
    private int selectedTeamID;
    
    private String userTeamJoinDate;
    private SimpleDateFormat dateFormatter;
    private Calendar userTeamJoinCalendar;
    
    @Inject
	private LoginBean loginBean;
    @Inject
	private NavigationBean navigationBean;
    
    @PostConstruct
    public void init() {
        teams = teamClubService.findAllTeams();
        if(loginBean.getLoggedInUserTeam() != null) selectedTeamID = loginBean.getLoggedInUserTeam().getID();
        dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
        userTeamJoinCalendar = Calendar.getInstance();
    }
    
    public String persistUserTeam() {
    	try {
			Team team = teamClubService.findTeam(selectedTeamID);
			Team assignedUserTeam = loginBean.getLoggedInUserTeam();
			if(team != null && (assignedUserTeam == null || team.getID() != assignedUserTeam.getID())) {
				Contact userContact = loginBean.getLoggedInUser().getContact();
				userTeamJoinCalendar.setTime(dateFormatter.parse(userTeamJoinDate));
				TeamContact teamContact = new TeamContact(team, userContact,userTeamJoinCalendar);
				userContact.getTeamContacts().add(teamContact);
				loginBean.setHasUserAssignedTeam(true);
				contactService.update(userContact);
				return navigationBean.redirectToTeamDataOverview();
			}
		} catch (TeamNotFoundException | ParseException e) {
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
