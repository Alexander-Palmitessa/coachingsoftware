/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.Draft;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Type;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "PLAYER")
public class Player {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PLAYER_ID")
	private int ID;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "FIRST_EMAIL", unique = true)
	private EmailAddress firstEmail;
	@Column(name = "SECOND_EMAIL", unique = true)
	private EmailAddress secondEmail;
	@Column(name = "CLUB")
	private Club club;
	@Column(name = "TYPE")
	private Type type;
	@Column(name = "DARFT")
	private Draft draft;
	@Column(name = "POSITION")
	private Position position;
	@Column(name = "PRIVATE_NUMBER")
	private Phonenumber privateNumber;
	@Column(name = "WORKING_NUMBER")
	private Phonenumber workingNumber;
	@Column(name = "MOBILE_NUMBER")
	private Phonenumber mobileNumber;
	@Column(name = "BIRTHDATE")
	private Calendar birthdate;


	//TODO: ANNOTATE
	private Set<Team> teams;
	private Set<Game> games;
	private Set<PlayerGameStats> gameStats;
	private Set<LineUp> lineUps;
	private Set<PerformanceDiagnostics> performanceDiagnostics;
	private Set<ScoutingReport> scoutingReports;
	private Set<EvaluationTalk> evaluationTalks;

	/*
	TODO: ZUGRIFFSRECHTE, SPIELBERECHTIGUNG(LAND)
	*/


	/**
	 * JPA required default constructor
	 */
	public Player() {

	}
}
