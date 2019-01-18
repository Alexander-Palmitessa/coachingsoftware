package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Card;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.PlayerGameStats;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

public class TotalPlayerStats {

	private Player player;

	private int totalGames;
	private int totalMinutes;
	private int totalIn;
	private int totalOut;
	private int totalGoals;
	private int totalAssist;
	private int totalYellow;
	private int totalYellowRed;
	private int totalRed;
	private double TIPSaverage;

	private ZoneCountPlayer goalZones;
	private ZoneCountPlayer assistZones;

	public TotalPlayerStats(Player player, StatisticsServiceRemote statisticsService, Season season) {
		this.player = player;
		totalGames = statisticsService.getNumberOfGames(season, player.getID());
		totalMinutes = statisticsService.getMinutesPlayed(season, player.getID());
		totalIn = statisticsService.getNumberOfChangeIn(season, player.getID());
		totalOut = statisticsService.getNumberOfChangeOut(season, player.getID());
		totalGoals = statisticsService.getNumberOfGoals(season, player.getID());
		totalAssist = statisticsService.getNumberOfAssists(season, player.getID());
		totalYellow = statisticsService.getNumberOfYellow(season, player.getID());
		totalYellowRed = statisticsService.getNumberOfYellowRed(season, player.getID());
		totalRed = statisticsService.getNumberOfRed(season, player.getID());
		TIPSaverage = statisticsService.getAverageTIPS(season, player.getID());
	}

	public int getTotalGames() {
		return totalGames;
	}

	public void setTotalGames(int totalGames) {
		this.totalGames = totalGames;
	}

	public int getTotalMinutes() {
		return totalMinutes;
	}

	public void setTotalMinutes(int totalMinutes) {
		this.totalMinutes = totalMinutes;
	}

	public int getTotalIn() {
		return totalIn;
	}

	public void setTotalIn(int totalIn) {
		this.totalIn = totalIn;
	}

	public int getTotalOut() {
		return totalOut;
	}

	public void setTotalOut(int totalOut) {
		this.totalOut = totalOut;
	}

	public int getTotalGoals() {
		return totalGoals;
	}

	public void setTotalGoals(int totalGoals) {
		this.totalGoals = totalGoals;
	}

	public int getTotalAssist() {
		return totalAssist;
	}

	public void setTotalAssist(int totalAssist) {
		this.totalAssist = totalAssist;
	}

	public int getTotalYellow() {
		return totalYellow;
	}

	public void setTotalYellow(int totalYellow) {
		this.totalYellow = totalYellow;
	}

	public int getTotalYellowRed() {
		return totalYellowRed;
	}

	public void setTotalYellowRed(int totalYellowRed) {
		this.totalYellowRed = totalYellowRed;
	}

	public int getTotalRed() {
		return totalRed;
	}

	public void setTotalRed(int totalRed) {
		this.totalRed = totalRed;
	}

	public double getTIPSaverage() {
		return TIPSaverage;
	}

	public void setTIPSaverage(double TIPSaverage) {
		this.TIPSaverage = TIPSaverage;
	}
}
