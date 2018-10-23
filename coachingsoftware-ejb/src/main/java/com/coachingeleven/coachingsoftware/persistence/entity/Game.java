/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.GameType;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {

	private static final long serialVersionUID = 8831699747010806773L;

	@Id
	@Column(name = "GAME_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	@Column(name = "TIME")
	@Temporal(TemporalType.TIME)
	private Calendar time;
	@ManyToOne
	@JoinColumn(name = "ARENA_ID")
	private Arena arena;
	@JoinColumn(name = "TEAM_HOME_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Team teamHome;
	@JoinColumn(name = "TEAM_AWAY_ID")
	@ManyToOne(fetch = FetchType.EAGER)
	private Team teamAway;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
	private Set<Goal> goals;
	@OneToOne(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PostGameReport postGameReport;
	@OneToOne(mappedBy = "game", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private PreGameReport preGameReport;
	@Enumerated(value = EnumType.STRING)
	@Column(name = "GAME_TYPE")
	private GameType gameType;
	@Column(name = "RES_GOALS_HOME")
	@Min(value = 0)
	private int resultGoalsHome;
	@Column(name = "RES_GOALS_AWAY")
	@Min(value = 0)
	private int resultGoalsAway;
	@OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<PlayerGameStats> playerGameStats;
	@JoinColumn(name = "ID_SEASON")
	@ManyToOne
	private Season season;
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "game")
	private Set<GameSystem> gameSystems;
	@OneToMany(mappedBy = "game", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<LineUpPlayer> lineUpPlayers;

	/**
	 * JPA required default constructor
	 */
	public Game() {

	}

	public int getID() {
		return ID;
	}


	public void setID(int iD) {
		ID = iD;
	}


	public Calendar getDate() {
		return date;
	}


	public void setDate(Calendar date) {
		this.date = date;
	}


	public Calendar getTime() {
		return time;
	}


	public void setTime(Calendar time) {
		this.time = time;
	}


	public Arena getArena() {
		return arena;
	}


	public void setArena(Arena arena) {
		this.arena = arena;
	}


	public Team getTeamHome() {
		return teamHome;
	}


	public void setTeamHome(Team teamHome) {
		this.teamHome = teamHome;
	}


	public Team getTeamAway() {
		return teamAway;
	}


	public void setTeamAway(Team teamAway) {
		this.teamAway = teamAway;
	}

	public PostGameReport getPostGameReport() {
		return postGameReport;
	}

	public void setPostGameReport(PostGameReport postGameReport) {
		this.postGameReport = postGameReport;
	}

	public PreGameReport getPreGameReport() {
		return preGameReport;
	}

	public void setPreGameReport(PreGameReport preGameReport) {
		this.preGameReport = preGameReport;
	}

	public Season getSeason() {
		return season;
	}

	public void setSeason(Season season) {
		this.season = season;
	}

	public GameType getGameType() {
		return gameType;
	}

	public void setGameType(GameType gameType) {
		this.gameType = gameType;
	}

	public int getResultGoalsHome() {
		return resultGoalsHome;
	}

	public void setResultGoalsHome(int resultGoalsHome) {
		this.resultGoalsHome = resultGoalsHome;
	}

	public Set<Goal> getGoals() {
		return goals;
	}

	public void setGoals(Set<Goal> goals) {
		this.goals = goals;
	}

	public int getResultGoalsAway() {
		return resultGoalsAway;
	}

	public void setResultGoalsAway(int resultGoalsAway) {
		this.resultGoalsAway = resultGoalsAway;
	}

	public Set<PlayerGameStats> getPlayerGameStats() {
		return playerGameStats;
	}

	public void setPlayerGameStats(Set<PlayerGameStats> playerGameStats) {
		this.playerGameStats = playerGameStats;
	}

	public Set<GameSystem> getGameSystems() {
		return gameSystems;
	}

	public void setGameSystems(Set<GameSystem> gameSystems) {
		this.gameSystems = gameSystems;
	}

	public void addGameSystem(GameSystem gameSystem) {
		this.gameSystems.add(gameSystem);
	}

	public Set<LineUpPlayer> getLineUpPlayers() {
		return lineUpPlayers;
	}

	public void setLineUpPlayers(Set<LineUpPlayer> lineUpPlayers) {
		this.lineUpPlayers = lineUpPlayers;
	}

}
