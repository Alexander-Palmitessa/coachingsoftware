package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.TeamContactServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;

@Named("teamContactBean")
@RequestScoped
public class TeamContactBean implements CreateBean<TeamContact>, Serializable {

	private static final long serialVersionUID = 2855013068618965009L;
	
	@Inject
	private DateFormatterBean dataFormatterBean;
	
	@EJB
    private TeamContactServiceRemote teamContactService;
	
	private TeamContact entity;
	
	private String successClass;
	private boolean createSuccess;
	
	private String joinDate;
	private String leaveDate;
	
	@PostConstruct
	public void init() {
		Team team = new Team();
		entity = new TeamContact();
		entity.setTeam(team);
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
