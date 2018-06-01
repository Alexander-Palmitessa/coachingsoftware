package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.SeasonAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.SeasonNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.repository.SeasonRepository;

@LocalBean
@Stateless(name = "SeasonService")
@TransactionAttribute(REQUIRED)
public class SeasonService implements SeasonServiceRemote {
	
	private static final Logger logger = Logger.getLogger(SeasonService.class.getName());

	@EJB
    private SeasonRepository seasonRepository;
	
	@Override
	public Season findSeason(int id) throws SeasonNotFoundException {
		logger.log(Level.INFO, "Finding season with id " + id);
        Season season = seasonRepository.find(id);
        if (season == null) {
            logger.log(Level.INFO, "Season not found");
            throw new SeasonNotFoundException();
        }
        return season;
	}

	@Override
	public Season createSeason(Season season) throws SeasonAlreadyExistsException {
		logger.log(Level.INFO, "Creating season with name " + season.getName());
        if (seasonRepository.find(season.getID()) != null) {
            logger.log(Level.INFO, "Season with id " + season.getName() + " already exists");
            throw new SeasonAlreadyExistsException();
        }
        return seasonRepository.persist(season);
	}

	@Override
	public List<Season> findAllSeasons() {
		return seasonRepository.findAll(Season.class);
	}

	@Override
	public List<Season> findSeasonsByTeam(int teamID) {
		return seasonRepository.findSeasonsByTeam(teamID);
	}

	@Override
	public Season updateSeason(Season season) {
		return seasonRepository.update(season);
	}

	@Override
	public Season addTeamToSeason(int seasonID, Team team) {
		return seasonRepository.addTeamToSeason(seasonID, team);
	}

}
