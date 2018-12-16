package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

import javax.ejb.Remote;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Remote
public interface StatisticsServiceRemote {
	int getPlayerGoalsA1(Season season, int playerID);

	int getPlayerGoalsA2(Season season, int playerID);

	int getPlayerGoalsB1(Season season, int playerID);

	int getPlayerGoalsB2(Season season, int playerID);

	int getPlayerGoalsC1(Season season, int playerID);

	int getPlayerGoalsC2(Season season, int playerID);

	int getPlayerGoalsD1(Season season, int playerID);

	int getPlayerGoalsD2(Season season, int playerID);

	int getPlayerGoalsE1(Season season, int playerID);

	int getPlayerGoalsE2(Season season, int playerID);

	int getPlayerGoalsF1(Season season, int playerID);

	int getPlayerGoalsF2(Season season, int playerID);

	int getPlayerAssistsA1(Season season, int playerID);

	int getPlayerAssistsA2(Season season, int playerID);

	int getPlayerAssistsB1(Season season, int playerID);

	int getPlayerAssistsB2(Season season, int playerID);

	int getPlayerAssistsC1(Season season, int playerID);

	int getPlayerAssistsC2(Season season, int playerID);

	int getPlayerAssistsD1(Season season, int playerID);

	int getPlayerAssistsD2(Season season, int playerID);

	int getPlayerAssistsE1(Season season, int playerID);

	int getPlayerAssistsE2(Season season, int playerID);

	int getPlayerAssistsF1(Season season, int playerID);

	int getPlayerAssistsF2(Season season, int playerID);

	int getTeamGoalsScoredA1(Season season, int teamID);

	int getTeamGoalsScoredA2(Season season, int teamID);

	int getTeamGoalsScoredB1(Season season, int teamID);

	int getTeamGoalsScoredB2(Season season, int teamID);

	int getTeamGoalsScoredC1(Season season, int teamID);

	int getTeamGoalsScoredC2(Season season, int teamID);

	int getTeamGoalsScoredD1(Season season, int teamID);

	int getTeamGoalsScoredD2(Season season, int teamID);

	int getTeamGoalsScoredE1(Season season, int teamID);

	int getTeamGoalsScoredE2(Season season, int teamID);

	int getTeamGoalsScoredF1(Season season, int teamID);

	int getTeamGoalsScoredF2(Season season, int teamID);

	int getTeamGoalsTakenA1(Season season, int teamID);

	int getTeamGoalsTakenA2(Season season, int teamID);

	int getTeamGoalsTakenB1(Season season, int teamID);

	int getTeamGoalsTakenB2(Season season, int teamID);

	int getTeamGoalsTakenC1(Season season, int teamID);

	int getTeamGoalsTakenC2(Season season, int teamID);

	int getTeamGoalsTakenD1(Season season, int teamID);

	int getTeamGoalsTakenD2(Season season, int teamID);

	int getTeamGoalsTakenE1(Season season, int teamID);

	int getTeamGoalsTakenE2(Season season, int teamID);

	int getTeamGoalsTakenF1(Season season, int teamID);

	int getTeamGoalsTakenF2(Season season, int teamID);

	int getTeamAssistsScoredA1(Season season, int teamID);

	int getTeamAssistsScoredA2(Season season, int teamID);

	int getTeamAssistsScoredB1(Season season, int teamID);

	int getTeamAssistsScoredB2(Season season, int teamID);

	int getTeamAssistsScoredC1(Season season, int teamID);

	int getTeamAssistsScoredC2(Season season, int teamID);

	int getTeamAssistsScoredD1(Season season, int teamID);

	int getTeamAssistsScoredD2(Season season, int teamID);

	int getTeamAssistsScoredE1(Season season, int teamID);

	int getTeamAssistsScoredE2(Season season, int teamID);

	int getTeamAssistsScoredF1(Season season, int teamID);

	int getTeamAssistsScoredF2(Season season, int teamID);

	int getTeamAssistsTakenA1(Season season, int teamID);

	int getTeamAssistsTakenA2(Season season, int teamID);

	int getTeamAssistsTakenB1(Season season, int teamID);

	int getTeamAssistsTakenB2(Season season, int teamID);

	int getTeamAssistsTakenC1(Season season, int teamID);

	int getTeamAssistsTakenC2(Season season, int teamID);

	int getTeamAssistsTakenD1(Season season, int teamID);

	int getTeamAssistsTakenD2(Season season, int teamID);

	int getTeamAssistsTakenE1(Season season, int teamID);

	int getTeamAssistsTakenE2(Season season, int teamID);

	int getTeamAssistsTakenF1(Season season, int teamID);

	int getTeamAssistsTakenF2(Season season, int teamID);
}
