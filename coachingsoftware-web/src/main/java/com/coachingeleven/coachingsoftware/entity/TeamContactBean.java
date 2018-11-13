package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

@Named("teamContactBean")
@RequestScoped
public class TeamContactBean implements CreateBean<TeamContact>, Serializable {

	private static final long serialVersionUID = 2855013068618965009L;
	
	@EJB
    private TeamContactServiceRemote teamContactService;
	
	private TeamContact entity;
	
	private String successClass;
	private boolean createSuccess;
	
	private String joinDate;
	private String leaveDate;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		Team team = new Team();
		entity = new TeamContact();
		entity.setTeam(team);
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	@Override
	public void create(TeamContact entity) {
		if(entity.getTeam().getID() > 0 && joinDate != null && joinDate != "") {
			try {
				Calendar calendar = Calendar.getInstance();
				if(joinDate != null) {
					calendar.setTime(dateFormatter.parse(joinDate));
					entity.setJoinDate(calendar);
				}
				if(leaveDate != null) {
					calendar.setTime(dateFormatter.parse(leaveDate));
					entity.getContact().setBirthdate(calendar);
				}
				teamContactService.createTeamContact(entity);
				successClass = "create-success";
				createSuccess = true;
			} catch (TeamContactAlreadyExistsException | ParseException e) {
				successClass = "create-failure";
				createSuccess = false;
			}
		}
	}
	
	public void create(TeamContact entity, Contact contact) {
		entity.setContact(contact);
		create(entity);
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
	
	public Team getFirstTeamByContact(Contact contact) {
		List<Team> teams = teamContactService.findTeamsByContact(contact);
		if(teams.size() > 0) return teams.get(0);
		return null;
	}
	
	public List<Team> getUnmanagedTeamByContact(Contact contact) {
		return teamContactService.findUnmanagedTeamsByContact(contact);
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

}
