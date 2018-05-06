/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {

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
	@ManyToOne
	private Team teamHome;
	@JoinColumn(name = "TEAM_AWAY_ID")
	@ManyToOne
	private Team teamAway;
	@ManyToMany(mappedBy = "games")
	private Set<Player> players;
	@JoinColumn(name = "GOALS_HOME_ID")
	@OneToMany
	private Set<Goal> goalsHome;
	@JoinColumn(name = "GOALS_AWAY_ID")
	@OneToMany
	private Set<Goal> goalsAway;
	@OneToMany(mappedBy = "game")
	private Set<Objective> objectives;
	@OneToOne(mappedBy = "game")
	private GameReport gameReport;


	//TODO: STATS etc. = INCOMPLETE

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


	public Set<Player> getPlayers() {
		return players;
	}


	public void setPlayers(Set<Player> players) {
		this.players = players;
	}


	public Set<Goal> getGoalsHome() {
		return goalsHome;
	}


	public void setGoalsHome(Set<Goal> goalsHome) {
		this.goalsHome = goalsHome;
	}


	public Set<Goal> getGoalsAway() {
		return goalsAway;
	}


	public void setGoalsAway(Set<Goal> goalsAway) {
		this.goalsAway = goalsAway;
	}


	public Set<Objective> getObjectives() {
		return objectives;
	}


	public void setObjectives(Set<Objective> objectives) {
		this.objectives = objectives;
	}


	public GameReport getGameReport() {
		return gameReport;
	}


	public void setGameReport(GameReport gameReport) {
		this.gameReport = gameReport;
	}
	
	
}
