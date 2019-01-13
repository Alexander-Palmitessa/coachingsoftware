package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.LoginBean;
import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.service.ContactServiceRemote;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.UpdateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContactId;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;

@Named("teamContactBean")
@RequestScoped
public class TeamContactBean implements CreateBean<TeamContact>, UpdateBean<TeamContact>, Serializable {

	private static final long serialVersionUID = 2855013068618965009L;
	
	private static final Logger logger = Logger.getLogger(TeamContactBean.class.getName());
	
	@Inject
	private DateFormatterBean dataFormatterBean;
	@Inject
	private LoginBean loginBean;
	
	@EJB
    private TeamContactServiceRemote teamContactService;
	@EJB
	private ContactServiceRemote contactService;
	
	private TeamContact entity;
	
	private String successClass;
	private boolean createSuccess;
	
	private String joinDate;
	private String tmpJoinDate;
	private String tmpPlayerJoinDate;
	private String leaveDate;
	
	@PostConstruct
	public void init() {
		Team team = new Team();
		entity = new TeamContact();
		entity.setTeam(team);
		if(loginBean.getLoggedInUser() != null && loginBean.getLoggedInUser().getContact() != null) {
			List<Team> teams = getAssignedTeamsByContact(loginBean.getLoggedInUser().getContact().getID());
			if(teams.size() > 0) {
				List<TeamContact> contacts = teamContactService.findAssignedTeamContacts(teams.get(0).getID(),loginBean.getLoggedInUser().getContact().getID());
				if(contacts.size() > 0) tmpJoinDate = dataFormatterBean.getFormattedDate(contacts.get(0).getJoinDate());
			}
			if(loginBean.getLoggedInUserTeam() != null) {
				List<Player> players = teamContactService.findPlayersByTeam(loginBean.getLoggedInUserTeam());
				if(players.size() > 0) {
					List<TeamContact> contacts = teamContactService.findAssignedTeamContacts(loginBean.getLoggedInUserTeam().getID(),players.get(0).getContact().getID());
					if(contacts.size() > 0) tmpPlayerJoinDate = dataFormatterBean.getFormattedDate(contacts.get(0).getJoinDate());
				}
			}
		}
	}
	
	@Override
	public void create(TeamContact entity) {
		if(entity.getTeam().getID() > 0 && joinDate != null && joinDate != "") {
			try {
				entity.setJoinDate(dataFormatterBean.getCalendar(joinDate));
				if(leaveDate != null && leaveDate != "") entity.setLeaveDate(dataFormatterBean.getCalendar(leaveDate));
				teamContactService.createTeamContact(entity);
				successClass = "create-success";
				createSuccess = true;
			} catch (TeamContactAlreadyExistsException e) {
				successClass = "create-failure";
				createSuccess = false;
			}
		}
	}
	
	public void create(TeamContact entity, Contact contact) {
		entity.setContact(contact);
		create(entity);
	}
	
	public void create(TeamContact entity, Team team, int contactID) {
		if(joinDate != null && joinDate != "" && team != null) {
			try {
				Contact dbPlayer = contactService.findContact(contactID);
				entity.setTeam(team);
				entity.setContact(dbPlayer);
				create(entity);
				successClass = "create-success";
				createSuccess = true;
			} catch (ContactNotFoundException e) {
				successClass = "create-failure";
				createSuccess = false;
			}
		}
	}
	
	@Override
	public void update(TeamContact entity) {
		successClass = "create-failure";
    	createSuccess = false;
    	teamContactService.update(entity);
    	successClass = "create-success";
    	createSuccess = true;
	}
	
	public void updateTeamContact(int teamID, int contactID, String tmpJoinDate, String leaveDate) {
		logger.warning("Team ID: " + teamID);
		logger.warning("Contact ID: " + contactID);
		logger.warning("Tmp Join Date: " + tmpJoinDate);
		logger.warning("Leave Date: " + leaveDate);
		successClass = "create-failure";
    	createSuccess = false;
    	if(tmpJoinDate != null) {
    		TeamContactId teamContactID = new TeamContactId(teamID, contactID, dataFormatterBean.getCalendar(tmpJoinDate));
			try {
				TeamContact teamContact = teamContactService.find(teamContactID);
				teamContact.setLeaveDate(dataFormatterBean.getCalendar(leaveDate));
				teamContactService.update(teamContact);
				successClass = "create-success";
		    	createSuccess = true;
		    	if(loginBean.getLoggedInUserTeam() != null && loginBean.getLoggedInUserTeam().getID() == teamID) loginBean.unassignCurrentTeam();
		    	if(loginBean.getLoggedInUser() != null) {
					List<Team> teams = getAssignedTeamsByContact(loginBean.getLoggedInUser().getContact().getID());
					if(teams.size() > 0) {
						Team tmpTeam = teams.get(0);
						List<TeamContact> contacts = teamContactService.findAssignedTeamContacts(tmpTeam.getID(),contactID);
						if(contacts.size() > 0) tmpJoinDate = dataFormatterBean.getFormattedDate(contacts.get(0).getJoinDate());
					}
				}
			} catch (TeamContactNotFoundException e) {
				logger.warning("Could not update team contact: " + e.getMessage());
			}
    	}
	}
	
	public void updatePlayerContact(int teamID, int contactID, String tmpPlayerJoinDate, String leaveDate) {
		successClass = "create-failure";
    	createSuccess = false;
    	if(tmpPlayerJoinDate != null) {
    		TeamContactId teamContactID = new TeamContactId(teamID, contactID, dataFormatterBean.getCalendar(tmpPlayerJoinDate));
			try {
				TeamContact teamContact = teamContactService.find(teamContactID);
				teamContact.setLeaveDate(dataFormatterBean.getCalendar(leaveDate));
				teamContactService.update(teamContact);
				successClass = "create-success";
		    	createSuccess = true;
			} catch (TeamContactNotFoundException e) {
				logger.warning("Could not update team contact: " + e.getMessage());
			}
    	}
	}
	
	public void setTmpJoinDateByTeam(int teamID, int contactID) {
		List<Team> teams = getAssignedTeamsByContact(contactID);
		if(teams.size() > 0) {
			List<TeamContact> contacts = teamContactService.findAssignedTeamContacts(teamID,contactID);
			if(contacts.size() > 0) tmpJoinDate = dataFormatterBean.getFormattedDate(contacts.get(0).getJoinDate());
		}
	}
	
	public void setTmpPlayerJoinDateByTeam(int teamID, int contactID) {
		List<Player> players = teamContactService.findPlayersByTeam(loginBean.getLoggedInUserTeam());
		if(players.size() > 0) {
			List<TeamContact> contacts = teamContactService.findAssignedTeamContacts(teamID,contactID);
			if(contacts.size() > 0) tmpPlayerJoinDate = dataFormatterBean.getFormattedDate(contacts.get(0).getJoinDate());
		}
	}
	
	public List<Player> findPlayersByTeam(Team team) {
		return teamContactService.findPlayersByTeam(team);
	}
	
	public List<Player> findPlayersByTeamAndSeason(Team team, Season season) {
		List<Player> players = teamContactService.findPlayersByTeamAndSeason(team.getID(), season);
		logger.warning("PLAYERS FOUND: " + players.size());
		return players;
	}

	@Override
	public String getSuccessClass() {
		return successClass;
	}

	@Override
	public boolean getCreateSuccess() {
		return createSuccess;
	}
	
	public List<Team> getTeamsByContact(Contact contact) {
		return teamContactService.findTeamsByContact(contact);
	}
	
	public List<Team> getAssignedTeamsByContact(int contactID) {
		if(loginBean.getLoggedInUserTeam() != null) {
			return teamContactService.findAssignedTeams(contactID);
		}
		return new ArrayList<Team>();
	}
	
	public List<Team> getUntrainedTeams() {
		return teamContactService.findUntrainedTeams();
	}
	
	public List<Player> getUnassingnedPlayers() {
		return teamContactService.findUnassingnedPlayers();
	}

	public TeamContact getEntity() {
		return entity;
	}

	public void setEntity(TeamContact entity) {
		this.entity = entity;
	}

	public String getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(String joinDate) {
		this.joinDate = joinDate;
	}

	public String getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(String leaveDate) {
		this.leaveDate = leaveDate;
	}

	public String getTmpJoinDate() {
		return tmpJoinDate;
	}

	public void setTmpJoinDate(String tmpJoinDate) {
		this.tmpJoinDate = tmpJoinDate;
	}

	public String getTmpPlayerJoinDate() {
		return tmpPlayerJoinDate;
	}

	public void setTmpPlayerJoinDate(String tmpPlayerJoinDate) {
		this.tmpPlayerJoinDate = tmpPlayerJoinDate;
	}

}
