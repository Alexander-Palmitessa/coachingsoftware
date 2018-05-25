package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

@Remote
public interface SeasonServiceRemote {
	public Season findSeason(int id) throws SeasonNotFoundException;
}
