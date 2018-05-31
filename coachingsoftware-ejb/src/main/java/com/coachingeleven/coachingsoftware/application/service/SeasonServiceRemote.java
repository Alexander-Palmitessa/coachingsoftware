package com.coachingeleven.coachingsoftware.application.service;

import java.util.List;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Remote
public interface SeasonServiceRemote {
	public Season findSeason(int id) throws SeasonNotFoundException;
	public Season createSeason(Season season) throws SeasonAlreadyExistsException;
	public List<Season> findAllSeasons();
	public List<Season> findSeasonsByTeam(int teamID);
}
