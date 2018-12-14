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
}
