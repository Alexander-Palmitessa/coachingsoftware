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
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "GAME")
public class Game {

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
	@Column(name = "ARENA")
	private Arena arena;
	@JoinColumn(name = "TEAM_HOME_ID")
	@ManyToOne
	private Team teamHome;
	@JoinColumn(name = "TEAM_AWAY_ID")
	@ManyToOne
	private Team teamAway;
	@ManyToMany(mappedBy = "games")
	private Set<Player> players;
	@Column(name = "GOALS_HOME")
	private int goalsHome;
	@Column(name = "GOALS_AWAY")
	private int goalsAway;
	@OneToMany(mappedBy = "GAME")
	private Set<Objective> objectives;
	@OneToOne(mappedBy = "GAME")
	private GameReport gameReport;


	//TODO: STATS etc. = UNCOMPLETE

	/**
	 * JPA required defualt constructor
	 */
	public Game() {

	}
}
