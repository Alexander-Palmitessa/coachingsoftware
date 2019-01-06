package com.coachingeleven.coachingsoftware.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.LoginBean;
import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.util.DateFormatterBean;

@Named("seasonBean")
@RequestScoped
public class SeasonBean implements EntityBean<Season>, CreateBean<Season>, Serializable {

	private static final long serialVersionUID = -2990308913039600698L;

	@Inject
	private DateFormatterBean dataFormatterBean;
	@Inject
	private TeamContactBean teamContactBean;
	@Inject
	private LoginBean loginBean;
	
	@EJB
    private SeasonServiceRemote seasonService;
	
	private Season entity;
	private List<Season> entities;
	private List<Season> tmpEntities;
	private String successClass;
	private boolean createSuccess;
	
	private String startDate;
	private String endDate;
	
	@PostConstruct
	public void init() {
		entity = new Season();
		entities = seasonService.findAllSeasons();
		tmpEntities = new ArrayList<Season>();
		if(loginBean.getLoggedInUser() != null) {
			List<Team> teams = teamContactBean.getTeamsByContact(loginBean.getLoggedInUser().getContact());
			if(teams.size() > 0) tmpEntities = seasonService.findSeasonsForAssignedTeam(teams.get(0).getID(),loginBean.getLoggedInUser().getContact().getID());
		}
	}
	
	@Override
	public void create(Season entity) {
		if(entity != null) {
			try {
				entity.setStartDate(dataFormatterBean.getCalendar(startDate));
				entity.setEndDate(dataFormatterBean.getCalendar(endDate));
				seasonService.createSeason(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (SeasonAlreadyExistsException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
	}
	
	public void updateTmpEntities(int teamId, int contactID) {
		tmpEntities = seasonService.findSeasonsForAssignedTeam(teamId, contactID);
	}
	
	public List<Season> getUnselectedSeasonsForTeam(int teamId, int contactID) {
		List<Season> unselectedSeasons = new ArrayList<Season>();
		List<Season> allSeasons = seasonService.findSeasonsForAssignedTeam(teamId, contactID);
		for(Season season : allSeasons) {
			if(season.getID() != loginBean.getLoggedInUserSeason().getID()) unselectedSeasons.add(season);
		}
		return unselectedSeasons;
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
	public Season getEntity() {
		return entity;
	}

	@Override
	public void setEntity(Season entity) {
		this.entity = entity;
	}

	@Override
	public List<Season> getEntities() {
		return entities;
	}

	@Override
	public void setEntities(List<Season> entities) {
		this.entities = entities;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<Season> getTmpEntities() {
		return tmpEntities;
	}

	public void setTmpEntities(List<Season> tmpEntities) {
		this.tmpEntities = tmpEntities;
	}

}
