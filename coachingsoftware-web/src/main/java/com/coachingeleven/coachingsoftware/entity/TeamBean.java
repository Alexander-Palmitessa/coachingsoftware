package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.TeamAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.TeamClubServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.entity.base.UpdateBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Club;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

@Named("teamBean")
@RequestScoped
public class TeamBean implements EntityBean<Team>, UpdateBean<Team>, CreateBean<Team>, Serializable {

	private static final long serialVersionUID = -4291011729695870803L;

	@EJB
    private TeamClubServiceRemote teamService;
	
	private Team entity;
	private List<Team> entities;
	private String successClass;
	private boolean createSuccess;
	
	@PostConstruct
	public void init() {
		Club newClub = new Club();
		entity = new Team();
		entity.setClub(newClub);
		entities = teamService.findAllTeams();
	}
	
	@Override
	public void create(Team entity) {
		if(entity != null) {
			try {
				teamService.createTeam(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (TeamAlreadyExistsException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
	}
	
	@Override
	public void update(Team entity) {
		successClass = "create-failure";
    	createSuccess = false;
		teamService.updateTeam(entity);
    	successClass = "create-success";
    	createSuccess = true;
	}

	@Override
	public String getSuccessClass() {
		return successClass;
	}

	@Override
	public boolean getCreateSuccess() {
		return createSuccess;
	}

	@Override
	public Team getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Team entity) {
		this.entity = entity;
	}

	@Override
	public List<Team> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Team> entities) {
		this.entities = entities;
	}
	
	public List<Team> getTeamsForContact(Contact contact) {
		return null;
	}

}
