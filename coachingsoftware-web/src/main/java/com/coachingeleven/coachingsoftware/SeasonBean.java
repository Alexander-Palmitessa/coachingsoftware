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

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Named("seasonBean")
@RequestScoped
public class SeasonBean {

	@EJB
	private SeasonServiceRemote seasonService;
	
	@Inject
	private NavigationBean navigationBean;
	
	private Season season;
	
	private String startDate;
	private String endDate;
	
	private SimpleDateFormat dateFormatter;
	
	private List<Season> seasons;
	
	@PostConstruct
    public void init() {
		if(season == null) season = new Season();
		dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
		seasons = seasonService.findAllSeasons();
    }
	
	public String createSeason() {
		try {
			Calendar startDateCalendar = Calendar.getInstance();
			Calendar endDateCalendar = Calendar.getInstance();
			startDateCalendar.setTime(dateFormatter.parse(startDate));
			endDateCalendar.setTime(dateFormatter.parse(endDate));
			
			season.setStartDate(startDateCalendar);
			season.setEndDate(endDateCalendar);
			
			seasonService.createSeason(season);
		} catch (SeasonAlreadyExistsException e) {
			// TODO
		} catch (ParseException e) {
			// TODO 
		}
		return navigationBean.redirectToSeasonForm();
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
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

	public List<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(List<Season> seasons) {
		this.seasons = seasons;
	}
   
}

