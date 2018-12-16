package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;

public class ZoneCountPlayer {

	//goals
	private int a1g;
	private int a2g;
	private int b1g;
	private int b2g;
	private int c1g;
	private int c2g;
	private int d1g;
	private int d2g;
	private int e1g;
	private int e2g;
	private int f1g;
	private int f2g;

	//assist
	private int a1a;
	private int a2a;
	private int b1a;
	private int b2a;
	private int c1a;
	private int c2a;
	private int d1a;
	private int d2a;
	private int e1a;
	private int e2a;
	private int f1a;
	private int f2a;

	public ZoneCountPlayer(StatisticsServiceRemote statisticsService, Season season, Player currentPlayer) {
		try {
			//set goals
			this.a1g = statisticsService.getPlayerGoalsA1(season, currentPlayer.getID());
			this.a2g = statisticsService.getPlayerGoalsA2(season, currentPlayer.getID());
			this.b1g = statisticsService.getPlayerGoalsB1(season, currentPlayer.getID());
			this.b2g = statisticsService.getPlayerGoalsB2(season, currentPlayer.getID());
			this.c1g = statisticsService.getPlayerGoalsC1(season, currentPlayer.getID());
			this.c2g = statisticsService.getPlayerGoalsC2(season, currentPlayer.getID());
			this.d1g = statisticsService.getPlayerGoalsD1(season, currentPlayer.getID());
			this.d2g = statisticsService.getPlayerGoalsD2(season, currentPlayer.getID());
			this.e1g = statisticsService.getPlayerGoalsE1(season, currentPlayer.getID());
			this.e2g = statisticsService.getPlayerGoalsE2(season, currentPlayer.getID());
			this.f1g = statisticsService.getPlayerGoalsF1(season, currentPlayer.getID());
			this.f2g = statisticsService.getPlayerGoalsF2(season, currentPlayer.getID());

			//set assists
			this.a1a = statisticsService.getPlayerAssistsA1(season, currentPlayer.getID());
			this.a2a = statisticsService.getPlayerAssistsA2(season, currentPlayer.getID());
			this.b1a = statisticsService.getPlayerAssistsB1(season, currentPlayer.getID());
			this.b2a = statisticsService.getPlayerAssistsB2(season, currentPlayer.getID());
			this.c1a = statisticsService.getPlayerAssistsC1(season, currentPlayer.getID());
			this.c2a = statisticsService.getPlayerAssistsC2(season, currentPlayer.getID());
			this.d1a = statisticsService.getPlayerAssistsD1(season, currentPlayer.getID());
			this.d2a = statisticsService.getPlayerAssistsD2(season, currentPlayer.getID());
			this.e1a = statisticsService.getPlayerAssistsE1(season, currentPlayer.getID());
			this.e2a = statisticsService.getPlayerAssistsE2(season, currentPlayer.getID());
			this.f1a = statisticsService.getPlayerAssistsF1(season, currentPlayer.getID());
			this.f2a = statisticsService.getPlayerAssistsF2(season, currentPlayer.getID());

		} catch (Exception e) {
			//TODO: ...
		}
	}

	public int getA1g() {
		return a1g;
	}

	public void setA1g(int a1g) {
		this.a1g = a1g;
	}

	public int getA2g() {
		return a2g;
	}

	public void setA2g(int a2g) {
		this.a2g = a2g;
	}

	public int getB1g() {
		return b1g;
	}

	public void setB1g(int b1g) {
		this.b1g = b1g;
	}

	public int getB2g() {
		return b2g;
	}

	public void setB2g(int b2g) {
		this.b2g = b2g;
	}

	public int getC1g() {
		return c1g;
	}

	public void setC1g(int c1g) {
		this.c1g = c1g;
	}

	public int getC2g() {
		return c2g;
	}

	public void setC2g(int c2g) {
		this.c2g = c2g;
	}

	public int getD1g() {
		return d1g;
	}

	public void setD1g(int d1g) {
		this.d1g = d1g;
	}

	public int getD2g() {
		return d2g;
	}

	public void setD2g(int d2g) {
		this.d2g = d2g;
	}

	public int getE1g() {
		return e1g;
	}

	public void setE1g(int e1g) {
		this.e1g = e1g;
	}

	public int getE2g() {
		return e2g;
	}

	public void setE2g(int e2g) {
		this.e2g = e2g;
	}

	public int getF1g() {
		return f1g;
	}

	public void setF1g(int f1g) {
		this.f1g = f1g;
	}

	public int getF2g() {
		return f2g;
	}

	public void setF2g(int f2g) {
		this.f2g = f2g;
	}

	public int getA1a() {
		return a1a;
	}

	public void setA1a(int a1a) {
		this.a1a = a1a;
	}

	public int getA2a() {
		return a2a;
	}

	public void setA2a(int a2a) {
		this.a2a = a2a;
	}

	public int getB1a() {
		return b1a;
	}

	public void setB1a(int b1a) {
		this.b1a = b1a;
	}

	public int getB2a() {
		return b2a;
	}

	public void setB2a(int b2a) {
		this.b2a = b2a;
	}

	public int getC1a() {
		return c1a;
	}

	public void setC1a(int c1a) {
		this.c1a = c1a;
	}

	public int getC2a() {
		return c2a;
	}

	public void setC2a(int c2a) {
		this.c2a = c2a;
	}

	public int getD1a() {
		return d1a;
	}

	public void setD1a(int d1a) {
		this.d1a = d1a;
	}

	public int getD2a() {
		return d2a;
	}

	public void setD2a(int d2a) {
		this.d2a = d2a;
	}

	public int getE1a() {
		return e1a;
	}

	public void setE1a(int e1a) {
		this.e1a = e1a;
	}

	public int getE2a() {
		return e2a;
	}

	public void setE2a(int e2a) {
		this.e2a = e2a;
	}

	public int getF1a() {
		return f1a;
	}

	public void setF1a(int f1a) {
		this.f1a = f1a;
	}

	public int getF2a() {
		return f2a;
	}

	public void setF2a(int f2a) {
		this.f2a = f2a;
	}
}
