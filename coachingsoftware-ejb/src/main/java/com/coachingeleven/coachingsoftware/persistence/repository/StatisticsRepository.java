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

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredA1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "A1") + getTeamGoalsScoredAway(season, teamID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredA2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "A2") + getTeamGoalsScoredAway(season, teamID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredB1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "B1") + getTeamGoalsScoredAway(season, teamID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredB2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "B2") + getTeamGoalsScoredAway(season, teamID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredC1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "C1") + getTeamGoalsScoredAway(season, teamID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredC2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "C2") + getTeamGoalsScoredAway(season, teamID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredD1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "D1") + getTeamGoalsScoredAway(season, teamID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredD2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "D2") + getTeamGoalsScoredAway(season, teamID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredE1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "E1") + getTeamGoalsScoredAway(season, teamID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredE2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "E2") + getTeamGoalsScoredAway(season, teamID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredF1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "F1") + getTeamGoalsScoredAway(season, teamID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredF2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, "F2") + getTeamGoalsScoredAway(season, teamID, "F2");
	}


	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenA1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "A1") + getTeamGoalsTakenAway(season, teamID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenA2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "A2") + getTeamGoalsTakenAway(season, teamID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenB1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "B1") + getTeamGoalsTakenAway(season, teamID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenB2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "B2") + getTeamGoalsTakenAway(season, teamID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenC1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "C1") + getTeamGoalsTakenAway(season, teamID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenC2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "C2") + getTeamGoalsTakenAway(season, teamID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenD1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "D1") + getTeamGoalsTakenAway(season, teamID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenD2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "D2") + getTeamGoalsTakenAway(season, teamID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenE1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "E1") + getTeamGoalsTakenAway(season, teamID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenE2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "E2") + getTeamGoalsTakenAway(season, teamID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenF1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "F1") + getTeamGoalsTakenAway(season, teamID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenF2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, "F2") + getTeamGoalsTakenAway(season, teamID, "F2");
	}


	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredA1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "A1") + getTeamAssistsScoredAway(season, teamID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredA2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "A2") + getTeamAssistsScoredAway(season, teamID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredB1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "B1") + getTeamAssistsScoredAway(season, teamID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredB2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "B2") + getTeamAssistsScoredAway(season, teamID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredC1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "C1") + getTeamAssistsScoredAway(season, teamID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredC2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "C2") + getTeamAssistsScoredAway(season, teamID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredD1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "D1") + getTeamAssistsScoredAway(season, teamID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredD2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "D2") + getTeamAssistsScoredAway(season, teamID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredE1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "E1") + getTeamAssistsScoredAway(season, teamID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredE2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "E2") + getTeamAssistsScoredAway(season, teamID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredF1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "F1") + getTeamAssistsScoredAway(season, teamID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredF2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, "F2") + getTeamAssistsScoredAway(season, teamID, "F2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenA1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "A1") + getTeamAssistsTakenAway(season, teamID, "A1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenA2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "A2") + getTeamAssistsTakenAway(season, teamID, "A2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenB1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "B1") + getTeamAssistsTakenAway(season, teamID, "B1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenB2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "B2") + getTeamAssistsTakenAway(season, teamID, "B2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenC1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "C1") + getTeamAssistsTakenAway(season, teamID, "C1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenC2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "C2") + getTeamAssistsTakenAway(season, teamID, "C2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenD1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "D1") + getTeamAssistsTakenAway(season, teamID, "D1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenD2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "D2") + getTeamAssistsTakenAway(season, teamID, "D2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenE1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "E1") + getTeamAssistsTakenAway(season, teamID, "E1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenE2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "E2") + getTeamAssistsTakenAway(season, teamID, "E2");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenF1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "F1") + getTeamAssistsTakenAway(season, teamID, "F1");
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenF2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, "F2") + getTeamAssistsTakenAway(season, teamID, "F2");
	}


	private int getPlayerGoals(Season season, int playerID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and SCORER_ID = " + playerID + " and ZONE_SCORE = '" + zone + "'").getSingleResult();
	}

	private int getPlayerAssists(Season season, int playerID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ASSISTANT_ID = " + playerID + " and ZONE_ASSIST = '" + zone + "'").getSingleResult();
	}


	// select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between
	// '1980-01-01' and '2020-01-01' and ZONE_SCORE = 'A1'  and GOALTYPE = 'SCORED' and TEAM_HOME_ID = 1;

	private int getTeamGoalsScoredHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsScoredAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsTakenHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsTakenAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}


	private int getTeamAssistsScoredHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsScoredAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsTakenHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsTakenAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE) +
				"' and '" +
				season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

}
