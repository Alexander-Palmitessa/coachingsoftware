package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Remote
public interface SeasonServiceRemote {
	Season findSeason(int id) throws SeasonNotFoundException;
	Season createSeason(Season season) throws SeasonAlreadyExistsException;
	List<Season> findAllSeasons();
	Season updateSeason(Season season);
}
