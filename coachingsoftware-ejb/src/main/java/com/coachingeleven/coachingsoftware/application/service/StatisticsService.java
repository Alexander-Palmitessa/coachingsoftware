package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.repository.StatisticsRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "statisticsService")
@TransactionAttribute(REQUIRED)
public class StatisticsService implements StatisticsServiceRemote {

	private static final Logger logger = Logger.getLogger(SeasonService.class.getName());

	@EJB
	private StatisticsRepository statisticsRepository;


	@Override
	public int getPlayerGoalsA1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsA1(season, playerID);
	}

	@Override
	public int getPlayerGoalsA2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsA2(season, playerID);
	}

	@Override
	public int getPlayerGoalsB1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoaslB1(season, playerID);
	}

	@Override
	public int getPlayerGoalsB2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsB2(season, playerID);
	}

	@Override
	public int getPlayerGoalsC1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsC1(season, playerID);
	}

	@Override
	public int getPlayerGoalsC2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsC2(season, playerID);
	}

	@Override
	public int getPlayerGoalsD1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsD1(season, playerID);
	}

	@Override
	public int getPlayerGoalsD2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsD2(season, playerID);
	}

	@Override
	public int getPlayerGoalsE1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsE1(season, playerID);
	}

	@Override
	public int getPlayerGoalsE2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsE2(season, playerID);
	}

	@Override
	public int getPlayerGoalsF1(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsF1(season, playerID);
	}

	@Override
	public int getPlayerGoalsF2(Season season, int playerID) {
		return statisticsRepository.getPlayerGoalsF2(season, playerID);
	}


}
