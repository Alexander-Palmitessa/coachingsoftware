package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

public class ZoneCount {

	private int a1;
	private int a2;
	private int b1;
	private int b2;
	private int c1;
	private int c2;
	private int d1;
	private int d2;
	private int e1;
	private int e2;
	private int f1;
	private int f2;

	public ZoneCount(StatisticsServiceRemote statisticsService, Season season, Player currentPlayer) {
		try {
			this.a1 = statisticsService.getPlayerGoalsA1(season, currentPlayer.getID());
			this.a2 = statisticsService.getPlayerGoalsA2(season, currentPlayer.getID());
			this.b1 = statisticsService.getPlayerGoalsB1(season, currentPlayer.getID());
			this.b2 = statisticsService.getPlayerGoalsB2(season, currentPlayer.getID());
			this.c1 = statisticsService.getPlayerGoalsC1(season, currentPlayer.getID());
			this.c2 = statisticsService.getPlayerGoalsC2(season, currentPlayer.getID());
			this.d1 = statisticsService.getPlayerGoalsD1(season, currentPlayer.getID());
			this.d2 = statisticsService.getPlayerGoalsD2(season, currentPlayer.getID());
			this.e1 = statisticsService.getPlayerGoalsE1(season, currentPlayer.getID());
			this.e2 = statisticsService.getPlayerGoalsE2(season, currentPlayer.getID());
			this.f1 = statisticsService.getPlayerGoalsF1(season, currentPlayer.getID());
			this.f2 = statisticsService.getPlayerGoalsF2(season, currentPlayer.getID());

		} catch (Exception e) {
			//TODO: ...
		}
	}

	public int getA1() {
		return a1;
	}

	public void setA1(int a1) {
		this.a1 = a1;
	}

	public int getA2() {
		return a2;
	}

	public void setA2(int a2) {
		this.a2 = a2;
	}

	public int getB1() {
		return b1;
	}

	public void setB1(int b1) {
		this.b1 = b1;
	}

	public int getB2() {
		return b2;
	}

	public void setB2(int b2) {
		this.b2 = b2;
	}

	public int getC1() {
		return c1;
	}

	public void setC1(int c1) {
		this.c1 = c1;
	}

	public int getC2() {
		return c2;
	}

	public void setC2(int c2) {
		this.c2 = c2;
	}

	public int getD1() {
		return d1;
	}

	public void setD1(int d1) {
		this.d1 = d1;
	}

	public int getD2() {
		return d2;
	}

	public void setD2(int d2) {
		this.d2 = d2;
	}

	public int getE1() {
		return e1;
	}

	public void setE1(int e1) {
		this.e1 = e1;
	}

	public int getE2() {
		return e2;
	}

	public void setE2(int e2) {
		this.e2 = e2;
	}

	public int getF1() {
		return f1;
	}

	public void setF1(int f1) {
		this.f1 = f1;
	}

	public int getF2() {
		return f2;
	}

	public void setF2(int f2) {
		this.f2 = f2;
	}
}
