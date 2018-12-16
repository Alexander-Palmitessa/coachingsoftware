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
}
