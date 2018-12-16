package com.coachingeleven.coachingsoftware.util;

import com.coachingeleven.coachingsoftware.application.service.StatisticsServiceRemote;
import com.coachingeleven.coachingsoftware.persistence.entity.Player;
import com.coachingeleven.coachingsoftware.persistence.entity.Season;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

public class ZoneCountTeam {

	//goals scored home+away
	private int a1gs;
	private int a2gs;
	private int b1gs;
	private int b2gs;
	private int c1gs;
	private int c2gs;
	private int d1gs;
	private int d2gs;
	private int e1gs;
	private int e2gs;
	private int f1gs;
	private int f2gs;

	//goals taken home+away
	private int a1gt;
	private int a2gt;
	private int b1gt;
	private int b2gt;
	private int c1gt;
	private int c2gt;
	private int d1gt;
	private int d2gt;
	private int e1gt;
	private int e2gt;
	private int f1gt;
	private int f2gt;

	//goals scored home+away
	private int a1as;
	private int a2as;
	private int b1as;
	private int b2as;
	private int c1as;
	private int c2as;
	private int d1as;
	private int d2as;
	private int e1as;
	private int e2as;
	private int f1as;
	private int f2as;

	//goals taken home+away
	private int a1at;
	private int a2at;
	private int b1at;
	private int b2at;
	private int c1at;
	private int c2at;
	private int d1at;
	private int d2at;
	private int e1at;
	private int e2at;
	private int f1at;
	private int f2at;

	public ZoneCountTeam(StatisticsServiceRemote statisticsService, Season season, Team currentTeam) {
		try {
			//set goals scored
			this.a1gs = statisticsService.getTeamGoalsScoredA1(season, currentTeam.getID());
			this.a2gs = statisticsService.getTeamGoalsScoredA2(season, currentTeam.getID());
			this.b1gs = statisticsService.getTeamGoalsScoredB1(season, currentTeam.getID());
			this.b2gs = statisticsService.getTeamGoalsScoredB2(season, currentTeam.getID());
			this.c1gs = statisticsService.getTeamGoalsScoredC1(season, currentTeam.getID());
			this.c2gs = statisticsService.getTeamGoalsScoredC2(season, currentTeam.getID());
			this.d1gs = statisticsService.getTeamGoalsScoredD1(season, currentTeam.getID());
			this.d2gs = statisticsService.getTeamGoalsScoredD2(season, currentTeam.getID());
			this.e1gs = statisticsService.getTeamGoalsScoredE1(season, currentTeam.getID());
			this.e2gs = statisticsService.getTeamGoalsScoredE2(season, currentTeam.getID());
			this.f1gs = statisticsService.getTeamGoalsScoredF1(season, currentTeam.getID());
			this.f2gs = statisticsService.getTeamGoalsScoredF2(season, currentTeam.getID());

			//set goals taken
			this.a1gt = statisticsService.getTeamGoalsTakenA1(season, currentTeam.getID());
			this.a2gt = statisticsService.getTeamGoalsTakenA2(season, currentTeam.getID());
			this.b1gt = statisticsService.getTeamGoalsTakenB1(season, currentTeam.getID());
			this.b2gt = statisticsService.getTeamGoalsTakenB2(season, currentTeam.getID());
			this.c1gt = statisticsService.getTeamGoalsTakenC1(season, currentTeam.getID());
			this.c2gt = statisticsService.getTeamGoalsTakenC2(season, currentTeam.getID());
			this.d1gt = statisticsService.getTeamGoalsTakenD1(season, currentTeam.getID());
			this.d2gt = statisticsService.getTeamGoalsTakenD2(season, currentTeam.getID());
			this.e1gt = statisticsService.getTeamGoalsTakenE1(season, currentTeam.getID());
			this.e2gt = statisticsService.getTeamGoalsTakenE2(season, currentTeam.getID());
			this.f1gt = statisticsService.getTeamGoalsTakenF1(season, currentTeam.getID());
			this.f2gt = statisticsService.getTeamGoalsTakenF2(season, currentTeam.getID());

			//set assists scored
			this.a1as = statisticsService.getTeamAssistsScoredA1(season, currentTeam.getID());
			this.a2as = statisticsService.getTeamAssistsScoredA2(season, currentTeam.getID());
			this.b1as = statisticsService.getTeamAssistsScoredB1(season, currentTeam.getID());
			this.b2as = statisticsService.getTeamAssistsScoredB2(season, currentTeam.getID());
			this.c1as = statisticsService.getTeamAssistsScoredC1(season, currentTeam.getID());
			this.c2as = statisticsService.getTeamAssistsScoredC2(season, currentTeam.getID());
			this.d1as = statisticsService.getTeamAssistsScoredD1(season, currentTeam.getID());
			this.d2as = statisticsService.getTeamAssistsScoredD2(season, currentTeam.getID());
			this.e1as = statisticsService.getTeamAssistsScoredE1(season, currentTeam.getID());
			this.e2as = statisticsService.getTeamAssistsScoredE2(season, currentTeam.getID());
			this.f1as = statisticsService.getTeamAssistsScoredF1(season, currentTeam.getID());
			this.f2as = statisticsService.getTeamAssistsScoredF2(season, currentTeam.getID());

			//set assists taken
			this.a1at = statisticsService.getTeamAssistsTakenA1(season, currentTeam.getID());
			this.a2at = statisticsService.getTeamAssistsTakenA2(season, currentTeam.getID());
			this.b1at = statisticsService.getTeamAssistsTakenB1(season, currentTeam.getID());
			this.b2at = statisticsService.getTeamAssistsTakenB2(season, currentTeam.getID());
			this.c1at = statisticsService.getTeamAssistsTakenC1(season, currentTeam.getID());
			this.c2at = statisticsService.getTeamAssistsTakenC2(season, currentTeam.getID());
			this.d1at = statisticsService.getTeamAssistsTakenD1(season, currentTeam.getID());
			this.d2at = statisticsService.getTeamAssistsTakenD2(season, currentTeam.getID());
			this.e1at = statisticsService.getTeamAssistsTakenE1(season, currentTeam.getID());
			this.e2at = statisticsService.getTeamAssistsTakenE2(season, currentTeam.getID());
			this.f1at = statisticsService.getTeamAssistsTakenF1(season, currentTeam.getID());
			this.f2at = statisticsService.getTeamAssistsTakenF2(season, currentTeam.getID());

		} catch (Exception e) {
			//TODO: ...
		}
	}

	public int getA1gs() {
		return a1gs;
	}

	public void setA1gs(int a1gs) {
		this.a1gs = a1gs;
	}

	public int getA2gs() {
		return a2gs;
	}

	public void setA2gs(int a2gs) {
		this.a2gs = a2gs;
	}

	public int getB1gs() {
		return b1gs;
	}

	public void setB1gs(int b1gs) {
		this.b1gs = b1gs;
	}

	public int getB2gs() {
		return b2gs;
	}

	public void setB2gs(int b2gs) {
		this.b2gs = b2gs;
	}

	public int getC1gs() {
		return c1gs;
	}

	public void setC1gs(int c1gs) {
		this.c1gs = c1gs;
	}

	public int getC2gs() {
		return c2gs;
	}

	public void setC2gs(int c2gs) {
		this.c2gs = c2gs;
	}

	public int getD1gs() {
		return d1gs;
	}

	public void setD1gs(int d1gs) {
		this.d1gs = d1gs;
	}

	public int getD2gs() {
		return d2gs;
	}

	public void setD2gs(int d2gs) {
		this.d2gs = d2gs;
	}

	public int getE1gs() {
		return e1gs;
	}

	public void setE1gs(int e1gs) {
		this.e1gs = e1gs;
	}

	public int getE2gs() {
		return e2gs;
	}

	public void setE2gs(int e2gs) {
		this.e2gs = e2gs;
	}

	public int getF1gs() {
		return f1gs;
	}

	public void setF1gs(int f1gs) {
		this.f1gs = f1gs;
	}

	public int getF2gs() {
		return f2gs;
	}

	public void setF2gs(int f2gs) {
		this.f2gs = f2gs;
	}

	public int getA1gt() {
		return a1gt;
	}

	public void setA1gt(int a1gt) {
		this.a1gt = a1gt;
	}

	public int getA2gt() {
		return a2gt;
	}

	public void setA2gt(int a2gt) {
		this.a2gt = a2gt;
	}

	public int getB1gt() {
		return b1gt;
	}

	public void setB1gt(int b1gt) {
		this.b1gt = b1gt;
	}

	public int getB2gt() {
		return b2gt;
	}

	public void setB2gt(int b2gt) {
		this.b2gt = b2gt;
	}

	public int getC1gt() {
		return c1gt;
	}

	public void setC1gt(int c1gt) {
		this.c1gt = c1gt;
	}

	public int getC2gt() {
		return c2gt;
	}

	public void setC2gt(int c2gt) {
		this.c2gt = c2gt;
	}

	public int getD1gt() {
		return d1gt;
	}

	public void setD1gt(int d1gt) {
		this.d1gt = d1gt;
	}

	public int getD2gt() {
		return d2gt;
	}

	public void setD2gt(int d2gt) {
		this.d2gt = d2gt;
	}

	public int getE1gt() {
		return e1gt;
	}

	public void setE1gt(int e1gt) {
		this.e1gt = e1gt;
	}

	public int getE2gt() {
		return e2gt;
	}

	public void setE2gt(int e2gt) {
		this.e2gt = e2gt;
	}

	public int getF1gt() {
		return f1gt;
	}

	public void setF1gt(int f1gt) {
		this.f1gt = f1gt;
	}

	public int getF2gt() {
		return f2gt;
	}

	public void setF2gt(int f2gt) {
		this.f2gt = f2gt;
	}

	public int getA1as() {
		return a1as;
	}

	public void setA1as(int a1as) {
		this.a1as = a1as;
	}

	public int getA2as() {
		return a2as;
	}

	public void setA2as(int a2as) {
		this.a2as = a2as;
	}

	public int getB1as() {
		return b1as;
	}

	public void setB1as(int b1as) {
		this.b1as = b1as;
	}

	public int getB2as() {
		return b2as;
	}

	public void setB2as(int b2as) {
		this.b2as = b2as;
	}

	public int getC1as() {
		return c1as;
	}

	public void setC1as(int c1as) {
		this.c1as = c1as;
	}

	public int getC2as() {
		return c2as;
	}

	public void setC2as(int c2as) {
		this.c2as = c2as;
	}

	public int getD1as() {
		return d1as;
	}

	public void setD1as(int d1as) {
		this.d1as = d1as;
	}

	public int getD2as() {
		return d2as;
	}

	public void setD2as(int d2as) {
		this.d2as = d2as;
	}

	public int getE1as() {
		return e1as;
	}

	public void setE1as(int e1as) {
		this.e1as = e1as;
	}

	public int getE2as() {
		return e2as;
	}

	public void setE2as(int e2as) {
		this.e2as = e2as;
	}

	public int getF1as() {
		return f1as;
	}

	public void setF1as(int f1as) {
		this.f1as = f1as;
	}

	public int getF2as() {
		return f2as;
	}

	public void setF2as(int f2as) {
		this.f2as = f2as;
	}

	public int getA1at() {
		return a1at;
	}

	public void setA1at(int a1at) {
		this.a1at = a1at;
	}

	public int getA2at() {
		return a2at;
	}

	public void setA2at(int a2at) {
		this.a2at = a2at;
	}

	public int getB1at() {
		return b1at;
	}

	public void setB1at(int b1at) {
		this.b1at = b1at;
	}

	public int getB2at() {
		return b2at;
	}

	public void setB2at(int b2at) {
		this.b2at = b2at;
	}

	public int getC1at() {
		return c1at;
	}

	public void setC1at(int c1at) {
		this.c1at = c1at;
	}

	public int getC2at() {
		return c2at;
	}

	public void setC2at(int c2at) {
		this.c2at = c2at;
	}

	public int getD1at() {
		return d1at;
	}

	public void setD1at(int d1at) {
		this.d1at = d1at;
	}

	public int getD2at() {
		return d2at;
	}

	public void setD2at(int d2at) {
		this.d2at = d2at;
	}

	public int getE1at() {
		return e1at;
	}

	public void setE1at(int e1at) {
		this.e1at = e1at;
	}

	public int getE2at() {
		return e2at;
	}

	public void setE2at(int e2at) {
		this.e2at = e2at;
	}

	public int getF1at() {
		return f1at;
	}

	public void setF1at(int f1at) {
		this.f1at = f1at;
	}

	public int getF2at() {
		return f2at;
	}

	public void setF2at(int f2at) {
		this.f2at = f2at;
	}
}
