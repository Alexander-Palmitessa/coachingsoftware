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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Calendar;

@Entity
@Table(name = "GAME")
public class Game {

	@Id
	@Column(name = "GAME ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int ID;
	@JoinColumn(name = "TEAM_HOME_ID")
	@ManyToOne
	private Team teamHome;
	@JoinColumn(name = "TEAM_AWAY_ID")
	@ManyToOne
	private Team teamAway;
	@Column(name = "DATE")
	private Calendar date;

	//TODO: TIME, STATS

	/**
	 * JPA required defualt constructor
	 */
	public Game(){

	}
}
