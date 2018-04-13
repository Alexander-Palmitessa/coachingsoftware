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
import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
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
	private String firstEmail;
	@Column(name = "SECOND_EMAIL", unique = true)
	private String secondEmail;
	@Column(name = "CLUB")
	@ManyToOne
	private Club club;
	@Column(name = "TYPE")
	@Enumerated(EnumType.STRING)
	private Role type;
	@Column(name = "DRAFT")
	@Enumerated(EnumType.STRING)
	private Draft draft;
	@Column(name = "POSITION")
	@Enumerated(EnumType.STRING)
	private Position position;
	@Column(name = "PRIVATE_NUMBER")
	private String privateNumber;
	@Column(name = "WORKING_NUMBER")
	private String workingNumber;
	@Column(name = "MOBILE_NUMBER")
	private String mobileNumber;
	@Column(name = "BIRTHDATE")
	@Temporal(TemporalType.DATE)
	private Calendar birthdate;
	@Column(name = "SIZE")
	private int size;
	@Column(name = "WEIGHT")
	private int weight;
	@Embedded
	private Contract contract;
	@JoinColumn(name = "COUNTRY")
	@ManyToOne
	private Country country;
	@ManyToMany
	@JoinTable(
			name = "PLAYER_TEAM",
			joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID"),
			inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID"))
	private Set<Team> teams;
	@ManyToMany
	@JoinTable(
			name = "PLAYER_GAME",
			joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID"),
			inverseJoinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID")
	)
	private Set<Game> games;
	@ManyToMany
	@JoinTable(
			name = "PLAYER_LINEUP",
			joinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID"),
			inverseJoinColumns = @JoinColumn(name = "LINEUP_ID", referencedColumnName = "LINEUP_ID")
	)
	private Set<LineUp> lineUps;
	@OneToMany
	private Set<PlayerGameStats> gameStats;
	@OneToMany(mappedBy = "PERF_DIAG")
	private Set<PerformanceDiagnostics> performanceDiagnostics;
	@OneToMany(mappedBy = "EVALUATION_TALK")
	private Set<EvaluationTalk> evaluationTalks;


	/**
	 * JPA required default constructor
	 */
	public Player() {

	}
}
