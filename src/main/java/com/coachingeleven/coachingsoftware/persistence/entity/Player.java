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
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
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
	@Column(name = "FIRST_NAME", nullable = false)
	private String firstName;
	@Column(name = "LAST_NAME", nullable = false)
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "FIRST_EMAIL", unique = true)
	private EmailAddress firstEmail;
	@Column(name = "SECOND_EMAIL", unique = true)
	private EmailAddress secondEmail;
	@Column(name = "CLUB")
	@ManyToOne
	private Club club;
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private Type type;
	@Column(name = "DARFT")
	@Enumerated(EnumType.STRING)
	private Draft draft;
	@Column(name = "POSITION")
	@Enumerated(EnumType.STRING)
	private Position position;
	@Column(name = "PRIVATE_NUMBER")
	private PhoneNumber privateNumber;
	@Column(name = "WORKING_NUMBER")
	private PhoneNumber workingNumber;
	@Column(name = "MOBILE_NUMBER")
	private PhoneNumber mobileNumber;
	@Column(name = "BIRTHDATE")
	private Calendar birthdate;


	/*
	Müssen die wirklich da sein oder reicht es diese bei den anderen
	Klassen mit @JoinColumn(name = "") zu annotieren?
	 */

	@Column(name = "TEAMS")
	@ManyToMany
	private Set<Team> teams;
	@Column(name = "GAMES")
	@ManyToMany
	private Set<Game> games;
	@Column(name = "GAMESTATS")
	@OneToMany
	private Set<PlayerGameStats> gameStats;
	@Column(name = "LINEUPS")
	@ManyToMany
	private Set<LineUp> lineUps;
	@Column(name = "PERFORMANCE_DIAGNASOTICS")
	@OneToMany
	private Set<PerformanceDiagnostics> performanceDiagnostics;
	@Column(name = "SCOUTING_REPORTS")
	@OneToMany
	private Set<ScoutingReport> scoutingReports;
	@Column(name = "EVALUATION_TALKS")
	@OneToMany
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
