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

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsA1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsA2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsB1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsB2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsC1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsC2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsD1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsD2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsE1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsE2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsF1(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsF2(Season season, int playerID) {
		return getPlayerAssists(season, playerID, "F2");
	}

	private int getPlayerGoals(Season season, int playerID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and SCORER_ID = 1 and ZONE_SCORE = '" + zone + "'").getSingleResult();
	}

	private int getPlayerAssists(Season season, int playerID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ASSISTANT_ID = 1 and ZONE_ASSIST = '" + zone + "'").getSingleResult();
	}
}
