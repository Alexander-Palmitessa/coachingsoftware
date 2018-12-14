package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.Calendar;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class StatisticsRepository extends Repository {

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsA1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsA2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoaslB1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsB2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsC1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsC2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsD1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsD2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsE1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsE2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsF1(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsF2(Season season, int playerID) {
		return getPlayerGoals(season, playerID, "F2");
	}


	// select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '2018-12-03' and '2018-12-05' and SCORER_ID = 1 and ZONE_SCORE = 'A1'

	//TODO: TEST + ASSIST + METHODS IN SERVICE
	private int getPlayerGoals(Season season, int playerID, String zone) {
		/*
		return entityManager.createQuery("SELECT COUNT(g) FROM Goal g JOIN Game game on g.game.ID = game.ID WHERE game.date BETWEEN :startDate AND :endDate AND LOWER(g.scoreZone) = LOWER(:zone) AND g.scorer = :playerID", Integer.class)
				.setParameter("startDate", season.getStartDate().get(Calendar.YEAR)+"-"+season.getStartDate().get(Calendar.MONTH)+"-"+season.getStartDate().get(Calendar.DATE))
				.setParameter("endDate", season.getEndDate().get(Calendar.YEAR)+"-"+season.getEndDate().get(Calendar.MONTH)+"-"+season.getEndDate().get(Calendar.DATE))
				.setParameter("zone", zone)
				.setParameter("playerID", playerID)
				.getSingleResult();
				*/
		return entityManager.createQuery("SELECT COUNT(g) FROM Goal g", Integer.class)
				.getSingleResult();
	}
}
