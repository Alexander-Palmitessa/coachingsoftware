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
	@Column(name = "TEAM_HOME")
	@ManyToOne
	private Team teamHome;
	@Column(name = "TEAM_AWAY")
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
