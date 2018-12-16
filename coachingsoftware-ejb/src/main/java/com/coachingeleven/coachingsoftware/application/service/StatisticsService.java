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

	@Override
	public int getPlayerAssistsA1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsA1(season, playerID);
	}

	@Override
	public int getPlayerAssistsA2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsA2(season, playerID);
	}

	@Override
	public int getPlayerAssistsB1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsB1(season, playerID);
	}

	@Override
	public int getPlayerAssistsB2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsB2(season, playerID);
	}

	@Override
	public int getPlayerAssistsC1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsC1(season, playerID);
	}

	@Override
	public int getPlayerAssistsC2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsC2(season, playerID);
	}

	@Override
	public int getPlayerAssistsD1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsD1(season, playerID);
	}

	@Override
	public int getPlayerAssistsD2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsD2(season, playerID);
	}

	@Override
	public int getPlayerAssistsE1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsE1(season, playerID);
	}

	@Override
	public int getPlayerAssistsE2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsE2(season, playerID);
	}

	@Override
	public int getPlayerAssistsF1(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsF1(season, playerID);
	}

	@Override
	public int getPlayerAssistsF2(Season season, int playerID) {
		return statisticsRepository.getPlayerAssistsF2(season, playerID);
	}

}
