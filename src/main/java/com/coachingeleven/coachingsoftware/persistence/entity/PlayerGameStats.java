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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "PLAYER_GAMESTATS")
public class PlayerGameStats implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYER_GAMESTATS_ID")
	int ID;
	@JoinColumn(name = "PLAYER")
	@ManyToOne
	private Player player;
	@Column(name = "MINUTES_PLAYED")
	private int minutesPlayed;
	@Column(name = "TOTAL_DISTANCE")
	private int totalDistance;
	@Column(name = "SPRINTS")
	private int sprints;
	@Column(name = "ACCELERATIONS")
	private int accelerations;
	@Column(name = "DECELERATIONS")
	private int decelerations;
	@Column(name = "MAX_SPEED")
	private int maxSpeed;
	@Column(name = "AVG_SPEED")
	private int avgSpeed;
	@Column(name = "SPRINT_DISTANCE")
	private int sprintDistance;
	@Column(name = "BALL_CONTACTS")
	private int ballContacts;
	@Column(name = "PASSES")
	private int passes;
	@Column(name = "PASSES_PERCENTAGE")
	private int passesPercentage;
	@Column(name = "DUELS")
	private int duels;
	@Column(name = "DUELS_PERCENTAGE")
	private int duelPercentage;
	@Column(name = "OFF_DUELS")
	private int offensiveDuels;
	@Column(name = "OFF_DUELS_PERCENTAGE")
	private int offensiveDuelsPercentage;
	@Column(name = "DEF_DUELS")
	private int defensiveDuels;
	@Column(name = "DEF_DUELS_PERCENTAGE")
	private int defensiveDuelsPercentage;
	@Column(name = "GOALS")
	private int goals;
	@Column(name = "ASSIST")
	private int assist;
	@Column(name = "LOAD_INDICATOR")
	private int loadIndicator; //TODO: ENTITY? (Borgskala 1-10)
	@OneToOne
	@JoinColumn(name = "CHANGEIN_ID")
	private ChangeIn changeIn;
	@OneToOne
	@JoinColumn(name = "CHANGEOUT_ID")
	private ChangeOut changeOut;

	//TODO: INCOMPLETE AUSWECHSLUNG(TYP DER AUSWECHSLUNG (Rot, Doppelgelb, normale Auswechslung))
	//TODO: WEITERES?

	/**
	 * JPA required default constructor
	 */
	public PlayerGameStats() {

	}

}
