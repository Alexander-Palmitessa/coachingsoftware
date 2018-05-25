package com.coachingeleven.coachingsoftware;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.service.SeasonServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Named("seasonBean")
@RequestScoped
public class SeasonBean {

	@EJB
	private SeasonServiceRemote seasonService;
	
	private Season season;
	
	@PostConstruct
    public void init() {
		if(season == null) season = new Season();
    }
	
	public void createSeason() {
		try {
			seasonService.createSeason(season);
		} catch (SeasonAlreadyExistsException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}
   
}

