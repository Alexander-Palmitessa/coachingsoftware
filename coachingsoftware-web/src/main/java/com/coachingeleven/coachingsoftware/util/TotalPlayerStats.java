package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.persistence.entity.Card;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.PlayerGameStats;

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

	//TODO: ONLY CURRENT SEASON

	public TotalPlayerStats(Player player) {
		this.player = player;
		createStats();
		countZones();
	}

	private void createStats() {
		totalGames = player.getGameStats().size();

		//get stats from every game
		for (PlayerGameStats pgs : player.getGameStats()) {
			totalMinutes += pgs.getMinutesPlayed();
			if (pgs.getChangeIn() != null) totalIn += 1;
			if (pgs.getChangeOut() != null) totalOut += 1;
			totalGoals += pgs.getGoals();
			totalAssist += pgs.getAssist();

			//get card stats
			for (Card card : pgs.getCards()) {
				switch (card.getType()) {
					case RED:
						totalRed += 1;
						break;
					case YELLOW:
						totalYellow += 1;
						break;
					case YELLOWRED:
						totalYellowRed += 1;
						break;
				}
			}

			//get averageTIPS
			TIPSaverage += pgs.getTips().getAverage();
		}
		TIPSaverage = (double) Math.round(TIPSaverage / player.getGameStats().size() * 100) / 100;
	}

	private void countZones(){
		//TODO: Count zones
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
