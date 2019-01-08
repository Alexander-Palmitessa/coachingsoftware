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
		return statisticsRepository.getPlayerGoalsB1(season, playerID);
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

	@Override
	public int getTeamGoalsScoredA1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredA1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredA2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredA2(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredB1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredB1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredB2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredB2(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredC1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredC1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredC2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredC2(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredD1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredD1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredD2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredD2(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredE1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredE1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredE2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredE2(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredF1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredF1(season, teamID);
	}

	@Override
	public int getTeamGoalsScoredF2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsScoredF2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenA1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenA1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenA2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenA2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenB1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenB1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenB2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenB2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenC1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenC1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenC2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenC2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenD1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenD1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenD2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenD2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenE1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenE1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenE2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenE2(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenF1(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenF1(season, teamID);
	}

	@Override
	public int getTeamGoalsTakenF2(Season season, int teamID) {
		return statisticsRepository.getTeamGoalsTakenF2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredA1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredA1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredA2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredA2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredB1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredB1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredB2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredB2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredC1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredC1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredC2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredC2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredD1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredD1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredD2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredD2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredE1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredE1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredE2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredE2(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredF1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredF1(season, teamID);
	}

	@Override
	public int getTeamAssistsScoredF2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsScoredF2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenA1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenA1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenA2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenA2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenB1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenB1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenB2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenB2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenC1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenC1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenC2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenC2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenD1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenD1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenD2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenD2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenE1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenE1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenE2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenE2(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenF1(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenF1(season, teamID);
	}

	@Override
	public int getTeamAssistsTakenF2(Season season, int teamID) {
		return statisticsRepository.getTeamAssistsTakenF2(season, teamID);
	}

	@Override
	public int get442GoalsScored(Season season, int teamID) {
		return statisticsRepository.get442GoalsScored(season, teamID);
	}

	@Override
	public int get442GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get442GoalsTaken(season, teamID);
	}

	@Override
	public int get424GoalsScored(Season season, int teamID) {
		return statisticsRepository.get424GoalsScored(season, teamID);
	}

	@Override
	public int get424GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get424GoalsTaken(season, teamID);
	}

	@Override
	public int get343GoalsScored(Season season, int teamID) {
		return statisticsRepository.get343GoalsScored(season, teamID);
	}

	@Override
	public int get343GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get343GoalsTaken(season, teamID);
	}

	@Override
	public int get433GoalsScored(Season season, int teamID) {
		return statisticsRepository.get433GoalsScored(season, teamID);
	}

	@Override
	public int get433GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get433GoalsTaken(season, teamID);
	}

	@Override
	public int get532GoalsScored(Season season, int teamID) {
		return statisticsRepository.get532GoalsScored(season, teamID);
	}

	@Override
	public int get532GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get532GoalsTaken(season, teamID);
	}

	@Override
	public int get352GoalsScored(Season season, int teamID) {
		return statisticsRepository.get352GoalsScored(season, teamID);
	}

	@Override
	public int get352GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get352GoalsTaken(season, teamID);
	}

	@Override
	public int get541GoalsScored(Season season, int teamID) {
		return statisticsRepository.get541GoalsScored(season, teamID);
	}

	@Override
	public int get541GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get541GoalsTaken(season, teamID);
	}

	@Override
	public int get451GoalsScored(Season season, int teamID) {
		return statisticsRepository.get451GoalsScored(season, teamID);
	}

	@Override
	public int get451GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get451GoalsTaken(season, teamID);
	}

	@Override
	public int get4231GoalsScored(Season season, int teamID) {
		return statisticsRepository.get4231GoalsScored(season, teamID);
	}

	@Override
	public int get4231GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get4231GoalsTaken(season, teamID);
	}

	@Override
	public int get4321GoalsScored(Season season, int teamID) {
		return statisticsRepository.get4321GoalsScored(season, teamID);
	}

	@Override
	public int get4321GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get4321GoalsTaken(season, teamID);
	}

	@Override
	public int get4141GoalsScored(Season season, int teamID) {
		return statisticsRepository.get4141GoalsScored(season, teamID);
	}

	@Override
	public int get4141GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get4141GoalsTaken(season, teamID);
	}

	@Override
	public int get334GoalsScored(Season season, int teamID) {
		return statisticsRepository.get334GoalsScored(season, teamID);
	}

	@Override
	public int get334GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get334GoalsTaken(season, teamID);
	}

	@Override
	public int get3313GoalsScored(Season season, int teamID) {
		return statisticsRepository.get3313GoalsScored(season, teamID);
	}

	@Override
	public int get3313GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get3313GoalsTaken(season, teamID);
	}

	@Override
	public int get460GoalsScored(Season season, int teamID) {
		return statisticsRepository.get460GoalsScored(season, teamID);
	}

	@Override
	public int get460GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get460GoalsTaken(season, teamID);
	}

	@Override
	public int get4222GoalsScored(Season season, int teamID) {
		return statisticsRepository.get4222GoalsScored(season, teamID);
	}

	@Override
	public int get4222GoalsTaken(Season season, int teamID) {
		return statisticsRepository.get4222GoalsTaken(season, teamID);
	}

	@Override
	public int getBlock0To15Scored(Season season, int teamID) {
		return statisticsRepository.getBlock0To15Scored(season, teamID);
	}

	@Override
	public int getBlock0To15Taken(Season season, int teamID) {
		return statisticsRepository.getBlock0To15Taken(season, teamID);
	}

	@Override
	public int getBlock15To30Scored(Season season, int teamID) {
		return statisticsRepository.getBlock15To30Scored(season, teamID);
	}

	@Override
	public int getBlock15To30Taken(Season season, int teamID) {
		return statisticsRepository.getBlock15To30Taken(season, teamID);
	}

	@Override
	public int getBlock30to45Scored(Season season, int teamID) {
		return statisticsRepository.getBlock30to45Scored(season, teamID);
	}

	@Override
	public int getBlock30to45Taken(Season season, int teamID) {
		return statisticsRepository.getBlock30to45Taken(season, teamID);
	}

	@Override
	public int getBlock45to60Scored(Season season, int teamID) {
		return statisticsRepository.getBlock45to60Scored(season, teamID);
	}

	@Override
	public int getBlock45to60Taken(Season season, int teamID) {
		return statisticsRepository.getBlock45to60Taken(season, teamID);
	}

	@Override
	public int getBlock60to75Scored(Season season, int teamID) {
		return statisticsRepository.getBlock60to75Scored(season, teamID);
	}

	@Override
	public int getBlock60to75Taken(Season season, int teamID) {
		return statisticsRepository.getBlock60to75Taken(season, teamID);
	}

	@Override
	public int getBlock75to90Scored(Season season, int teamID) {
		return statisticsRepository.getBlock75to90Scored(season, teamID);
	}

	@Override
	public int getBlock75to90Taken(Season season, int teamID) {
		return statisticsRepository.getBlock75to90Taken(season, teamID);
	}

	@Override
	public double getAverageTIPS(Season season, int playerID){
		return statisticsRepository.getAverageTIPS(season, playerID);
	}

	@Override
	public int getNumberOfGames(Season season, int playerID) {
		return statisticsRepository.getPlayerNumberOfGames(season, playerID);
	}

	@Override
	public int getMinutesPlayed(Season season, int playerID) {
		return statisticsRepository.getNumberOfMinutesPlayed(season, playerID);
	}

	@Override
	public int getNumberOfGoals(Season season, int playerID) {
		return statisticsRepository.getNumberOfGoals(season, playerID);
	}

	@Override
	public int getNumberOfAssists(Season season, int playerID) {
		return statisticsRepository.getNumberOfAssists(season, playerID);
	}

	@Override
	public int getNumberOfChangeIn(Season season, int playerID) {
		return statisticsRepository.getNumberOfChangeIns(season, playerID);
	}

	@Override
	public int getNumberOfChangeOut(Season season, int playerID) {
		return statisticsRepository.getNumberOfChangeOuts(season, playerID);
	}

	@Override
	public int getNumberOfRed(Season season, int playerID) {
		return 0;
	}

	@Override
	public int getNumberOfYellow(Season season, int playerID) {
		return 0;
	}

	@Override
	public int getNumberOfYellowRed(Season season, int playerID) {
		return 0;
	}


}
