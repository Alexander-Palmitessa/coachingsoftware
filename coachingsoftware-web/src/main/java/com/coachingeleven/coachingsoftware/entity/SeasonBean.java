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

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.entity.base.CreateBean;
import com.coachingeleven.coachingsoftware.entity.base.EntityBean;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Named("seasonBean")
@RequestScoped
public class SeasonBean implements EntityBean<Season>, CreateBean<Season>, Serializable {

	private static final long serialVersionUID = -2990308913039600698L;

	@EJB
    private SeasonServiceRemote seasonService;
	
	private Season entity;
	private List<Season> entities;
	private String successClass;
	private boolean createSuccess;
	
	private String startDate;
	private String endDate;
	
	private SimpleDateFormat dateFormatter;
	
	@PostConstruct
	public void init() {
		entity = new Season();
		entities = seasonService.findAllSeasons();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
	}
	
	@Override
	public void create(Season entity) {
		if(entity != null) {
			try {
				Calendar startDateCalendar = Calendar.getInstance();
				Calendar endDateCalendar = Calendar.getInstance();
				startDateCalendar.setTime(dateFormatter.parse(startDate));
				endDateCalendar.setTime(dateFormatter.parse(endDate));
				
				entity.setStartDate(startDateCalendar);
				entity.setEndDate(endDateCalendar);
				seasonService.createSeason(entity);
	        	successClass = "create-success";
	        	createSuccess = true;
	        } catch (SeasonAlreadyExistsException | ParseException e) {
	        	successClass = "create-failure";
	        	createSuccess = false;
	        }
		}
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

}
