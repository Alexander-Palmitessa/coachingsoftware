package com.coachingeleven.coachingsoftware;

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
	
	@PostConstruct
    public void init() {
		if(season == null) season = new Season();
    }
	
	public String createSeason() {
		try {
			seasonService.createSeason(season);
		} catch (SeasonAlreadyExistsException e) {
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
   
}

