package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.Calendar;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class StatisticsRepository extends Repository {

	final String SCORED = "SCORED";
	final String TAKEN = "TAKEN";
	final String T442 = "T442";
	final String T424 = "T424";
	final String T343 = "T343";
	final String T433 = "T433";
	final String T532 = "T532";
	final String T352 = "T352";
	final String T541 = "T541";
	final String T451 = "T451";
	final String T4231 = "T4231";
	final String T4321 = "T4321";
	final String T4141 = "T4141";
	final String T334 = "T334";
	final String T3313 = "T3313";
	final String T460 = "T460";
	final String T4222 = "T4222";


	final String A1 = "A1";
	final String A2 = "A2";
	final String B1 = "B1";
	final String B2 = "B2";
	final String C1 = "C1";
	final String C2 = "C2";
	final String D1 = "D1";
	final String D2 = "D2";
	final String E1 = "E1";
	final String E2 = "E2";
	final String F1 = "F1";
	final String F2 = "F2";

	final String ZONE_SCORED = "ZONE_SCORE";
	final String ZONE_ASSIST = "ZONE_ASSIST";
	final String SCORER_ID = "SCORER_ID";
	final String ASSISTANT_ID = "ASSISTANT_ID";
	final String TEAM_HOME_ID = "TEAM_HOME_ID";
	final String TEAM_AWAY_ID = "TEAM_AWAY_ID";

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsA1(Season season, int playerID) {
		return getPlayerZones(season, playerID, A1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsA2(Season season, int playerID) {
		return getPlayerZones(season, playerID, A2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsB1(Season season, int playerID) {
		return getPlayerZones(season, playerID, B1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsB2(Season season, int playerID) {
		return getPlayerZones(season, playerID, B2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsC1(Season season, int playerID) {
		return getPlayerZones(season, playerID, C1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsC2(Season season, int playerID) {
		return getPlayerZones(season, playerID, C2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsD1(Season season, int playerID) {
		return getPlayerZones(season, playerID, D1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsD2(Season season, int playerID) {
		return getPlayerZones(season, playerID, D2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsE1(Season season, int playerID) {
		return getPlayerZones(season, playerID, E1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsE2(Season season, int playerID) {
		return getPlayerZones(season, playerID, E2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsF1(Season season, int playerID) {
		return getPlayerZones(season, playerID, F1, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerGoalsF2(Season season, int playerID) {
		return getPlayerZones(season, playerID, F2, SCORER_ID, ZONE_SCORED);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsA1(Season season, int playerID) {
		return getPlayerZones(season, playerID, A1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsA2(Season season, int playerID) {
		return getPlayerZones(season, playerID, A2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsB1(Season season, int playerID) {
		return getPlayerZones(season, playerID, B1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsB2(Season season, int playerID) {
		return getPlayerZones(season, playerID, B2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsC1(Season season, int playerID) {
		return getPlayerZones(season, playerID, C1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsC2(Season season, int playerID) {
		return getPlayerZones(season, playerID, C2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsD1(Season season, int playerID) {
		return getPlayerZones(season, playerID, D1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsD2(Season season, int playerID) {
		return getPlayerZones(season, playerID, D2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsE1(Season season, int playerID) {
		return getPlayerZones(season, playerID, E1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsE2(Season season, int playerID) {
		return getPlayerZones(season, playerID, E2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsF1(Season season, int playerID) {
		return getPlayerZones(season, playerID, F1, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getPlayerAssistsF2(Season season, int playerID) {
		return getPlayerZones(season, playerID, F2, ASSISTANT_ID, ZONE_ASSIST);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredA1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, A1) + getTeamGoalsScoredAway(season, teamID, A1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredA2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, A2) + getTeamGoalsScoredAway(season, teamID, A2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredB1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, B1) + getTeamGoalsScoredAway(season, teamID, B1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredB2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, B2) + getTeamGoalsScoredAway(season, teamID, B2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredC1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, C1) + getTeamGoalsScoredAway(season, teamID, C1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredC2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, C2) + getTeamGoalsScoredAway(season, teamID, C2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredD1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, D1) + getTeamGoalsScoredAway(season, teamID, D1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredD2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, D2) + getTeamGoalsScoredAway(season, teamID, D2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredE1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, E1) + getTeamGoalsScoredAway(season, teamID, E1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredE2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, E2) + getTeamGoalsScoredAway(season, teamID, E2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredF1(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, F1) + getTeamGoalsScoredAway(season, teamID, F1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsScoredF2(Season season, int teamID) {
		return getTeamGoalsScoredHome(season, teamID, F2) + getTeamGoalsScoredAway(season, teamID, F2);
	}


	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenA1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, A1) + getTeamGoalsTakenAway(season, teamID, A1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenA2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, A2) + getTeamGoalsTakenAway(season, teamID, A2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenB1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, B1) + getTeamGoalsTakenAway(season, teamID, B1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenB2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, B2) + getTeamGoalsTakenAway(season, teamID, B2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenC1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, C1) + getTeamGoalsTakenAway(season, teamID, C1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenC2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, C2) + getTeamGoalsTakenAway(season, teamID, C2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenD1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, D1) + getTeamGoalsTakenAway(season, teamID, D1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenD2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, D2) + getTeamGoalsTakenAway(season, teamID, D2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenE1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, E1) + getTeamGoalsTakenAway(season, teamID, E1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenE2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, E2) + getTeamGoalsTakenAway(season, teamID, E2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenF1(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, F1) + getTeamGoalsTakenAway(season, teamID, F1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamGoalsTakenF2(Season season, int teamID) {
		return getTeamGoalsTakenHome(season, teamID, F2) + getTeamGoalsTakenAway(season, teamID, F2);
	}


	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredA1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, A1) + getTeamAssistsScoredAway(season, teamID, A1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredA2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, A2) + getTeamAssistsScoredAway(season, teamID, A2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredB1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, B1) + getTeamAssistsScoredAway(season, teamID, B1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredB2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, B2) + getTeamAssistsScoredAway(season, teamID, B2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredC1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, C1) + getTeamAssistsScoredAway(season, teamID, C1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredC2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, C2) + getTeamAssistsScoredAway(season, teamID, C2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredD1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, D1) + getTeamAssistsScoredAway(season, teamID, D1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredD2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, D2) + getTeamAssistsScoredAway(season, teamID, D2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredE1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, E1) + getTeamAssistsScoredAway(season, teamID, E1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredE2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, E2) + getTeamAssistsScoredAway(season, teamID, E2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredF1(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, F1) + getTeamAssistsScoredAway(season, teamID, F1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsScoredF2(Season season, int teamID) {
		return getTeamAssistsScoredHome(season, teamID, F2) + getTeamAssistsScoredAway(season, teamID, F2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenA1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, A1) + getTeamAssistsTakenAway(season, teamID, A1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenA2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, A2) + getTeamAssistsTakenAway(season, teamID, A2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenB1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, B1) + getTeamAssistsTakenAway(season, teamID, B1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenB2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, B2) + getTeamAssistsTakenAway(season, teamID, B2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenC1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, C1) + getTeamAssistsTakenAway(season, teamID, C1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenC2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, C2) + getTeamAssistsTakenAway(season, teamID, C2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenD1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, D1) + getTeamAssistsTakenAway(season, teamID, D1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenD2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, D2) + getTeamAssistsTakenAway(season, teamID, D2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenE1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, E1) + getTeamAssistsTakenAway(season, teamID, E1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenE2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, E2) + getTeamAssistsTakenAway(season, teamID, E2);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenF1(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, F1) + getTeamAssistsTakenAway(season, teamID, F1);
	}

	@TransactionAttribute(SUPPORTS)
	public int getTeamAssistsTakenF2(Season season, int teamID) {
		return getTeamAssistsTakenHome(season, teamID, F2) + getTeamAssistsTakenAway(season, teamID, F2);
	}


	@TransactionAttribute(SUPPORTS)
	public int get442GoalsScored(Season season, int teamID) {
		return getSystemGoals(T442, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get442GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T442, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get424GoalsScored(Season season, int teamID) {
		return getSystemGoals(T424, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get424GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T424, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get343GoalsScored(Season season, int teamID) {
		return getSystemGoals(T343, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get343GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T343, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get433GoalsScored(Season season, int teamID) {
		return getSystemGoals(T433, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get433GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T433, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get532GoalsScored(Season season, int teamID) {
		return getSystemGoals(T532, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get532GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T532, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get352GoalsScored(Season season, int teamID) {
		return getSystemGoals(T352, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get352GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T352, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get541GoalsScored(Season season, int teamID) {
		return getSystemGoals(T541, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get541GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T541, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get451GoalsScored(Season season, int teamID) {
		return getSystemGoals(T451, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get451GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T451, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4231GoalsScored(Season season, int teamID) {
		return getSystemGoals(T4231, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4231GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T4231, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4321GoalsScored(Season season, int teamID) {
		return getSystemGoals(T4321, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4321GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T4321, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4141GoalsScored(Season season, int teamID) {
		return getSystemGoals(T4141, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4141GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T4141, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get334GoalsScored(Season season, int teamID) {
		return getSystemGoals(T334, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get334GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T334, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get3313GoalsScored(Season season, int teamID) {
		return getSystemGoals(T3313, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get3313GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T3313, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get460GoalsScored(Season season, int teamID) {
		return getSystemGoals(T460, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get460GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T460, season, TAKEN, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4222GoalsScored(Season season, int teamID) {
		return getSystemGoals(T4222, season, SCORED, teamID);
	}

	@TransactionAttribute(SUPPORTS)
	public int get4222GoalsTaken(Season season, int teamID) {
		return getSystemGoals(T4222, season, TAKEN, teamID);
	}

	public String getStartDate(Season season) {
		return season.getStartDate().get(Calendar.YEAR) + "-" + season.getStartDate().get(Calendar.MONTH) + 1 + "-" + season.getStartDate().get(Calendar.DATE);
	}

	public String getEndDate(Season season) {
		return season.getEndDate().get(Calendar.YEAR) + "-" + season.getEndDate().get(Calendar.MONTH) + 1 + "-" + season.getEndDate().get(Calendar.DATE);
	}

	private int getPlayerZones(Season season, int playerID, String zone, String scorerAssistant, String scoreAssistZone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and " + scorerAssistant + " = " + playerID + " and " + scoreAssistZone + " = '" + zone + "'").getSingleResult();
	}

	// select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between
	// '1980-01-01' and '2020-01-01' and ZONE_SCORE = 'A1'  and GOALTYPE = 'SCORED' and TEAM_HOME_ID = 1;

	private int getTeamGoalsScoredHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsScoredAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsTakenHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) +
				"' and '" + getEndDate(season)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamGoalsTakenAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_SCORE = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}


	private int getTeamAssistsScoredHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsScoredAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'SCORED' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsTakenHome(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_HOME_ID = " + teamID).getSingleResult();
	}

	private int getTeamAssistsTakenAway(Season season, int teamID, String zone) {
		return (int) entityManager.createNativeQuery("select count(*) from GOAL join GAME G on GOAL.GAME_ID = G.GAME_ID where cast(DATE as date) between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and ZONE_ASSIST = '" + zone + "' and GOALTYPE = 'TAKEN' and TEAM_AWAY_ID = " + teamID).getSingleResult();
	}

	//select count(*) from GAME_SYS join GOAL G on GAME_SYS_ID=G.GAME_ID join GAME G2 on G.GAME_ID = G2.GAME_ID where DATE between '1990-01-01' and '2020-01-01' and TEAM_AWAY_ID=1 or TEAM_HOME_ID=1 and GOALTYPE='SCORED' and SYSTEM='X'
	private int getSystemGoals(String system, Season season, String scoredTaken, int teamID) {
		return (int) entityManager.createNativeQuery("select count(*) from GAME_SYS join GOAL G on GAME_SYS_ID=G.GAME_ID join GAME G2 on G.GAME_ID = G2.GAME_ID where DATE between '" +
				getStartDate(season) + "' and '" + getEndDate(season)
				+ "' and TEAM_AWAY_ID=" + teamID + " or TEAM_HOME_ID=" + teamID + " and GOALTYPE='" + scoredTaken + "' and SYSTEM='" + system + "'\n").getSingleResult();
	}
}
