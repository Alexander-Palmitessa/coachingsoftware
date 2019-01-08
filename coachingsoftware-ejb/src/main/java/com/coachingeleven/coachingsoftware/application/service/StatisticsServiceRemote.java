package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.persistence.entity.Season;

import javax.ejb.Remote;

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

	int get442GoalsScored(Season season, int teamID);

	int get442GoalsTaken(Season season, int teamID);

	int get424GoalsScored(Season season, int teamID);

	int get424GoalsTaken(Season season, int teamID);

	int get343GoalsScored(Season season, int teamID);

	int get343GoalsTaken(Season season, int teamID);

	int get433GoalsScored(Season season, int teamID);

	int get433GoalsTaken(Season season, int teamID);

	int get532GoalsScored(Season season, int teamID);

	int get532GoalsTaken(Season season, int teamID);

	int get352GoalsScored(Season season, int teamID);

	int get352GoalsTaken(Season season, int teamID);

	int get541GoalsScored(Season season, int teamID);

	int get541GoalsTaken(Season season, int teamID);

	int get451GoalsScored(Season season, int teamID);

	int get451GoalsTaken(Season season, int teamID);

	int get4231GoalsScored(Season season, int teamID);

	int get4231GoalsTaken(Season season, int teamID);

	int get4321GoalsScored(Season season, int teamID);

	int get4321GoalsTaken(Season season, int teamID);

	int get4141GoalsScored(Season season, int teamID);

	int get4141GoalsTaken(Season season, int teamID);

	int get334GoalsScored(Season season, int teamID);

	int get334GoalsTaken(Season season, int teamID);

	int get3313GoalsScored(Season season, int teamID);

	int get3313GoalsTaken(Season season, int teamID);

	int get460GoalsScored(Season season, int teamID);

	int get460GoalsTaken(Season season, int teamID);

	int get4222GoalsScored(Season season, int teamID);

	int get4222GoalsTaken(Season season, int teamID);

	int getBlock0To15Scored(Season season, int teamID);

	int getBlock0To15Taken(Season season, int teamID);

	int getBlock15To30Scored(Season season, int teamID);

	int getBlock15To30Taken(Season season, int teamID);

	int getBlock30to45Scored(Season season, int teamID);

	int getBlock30to45Taken(Season season, int teamID);

	int getBlock45to60Scored(Season season, int teamID);

	int getBlock45to60Taken(Season season, int teamID);

	int getBlock60to75Scored(Season season, int teamID);

	int getBlock60to75Taken(Season season, int teamID);

	int getBlock75to90Scored(Season season, int teamID);

	int getBlock75to90Taken(Season season, int teamID);

	double getAverageTIPS(Season season, int playerID);

	int getNumberOfGames(Season season, int playerID);

	int getMinutesPlayed(Season season, int playerID);

	int getNumberOfGoals(Season season, int playerID);

	int getNumberOfAssists(Season season, int playerID);

	int getNumberOfChangeIn(Season season, int playerID);

	int getNumberOfChangeOut(Season season, int playerID);

	int getNumberOfRed(Season season, int playerID);

	int getNumberOfYellow(Season season, int playerID);

	int getNumberOfYellowRed(Season season, int playerID);
}
