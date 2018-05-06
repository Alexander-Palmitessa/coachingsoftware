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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

@Entity
@Table(name = "PLAYER")
@NamedQueries({
	@NamedQuery(name = "findPlayer",
			query = "SELECT c FROM Player c WHERE LOWER(c.firstEmail) = LOWER(:email)")
})
public class Player implements Serializable {

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
	@JoinColumn(name = "CLUB_ID")
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
	@JoinColumn(name = "COUNTRY_PERMISSION_ID")
	@ManyToOne
	private Country countryPermission;
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
	@OneToMany(mappedBy = "player")
	private Set<PerformanceDiagnostics> performanceDiagnostics;
	@OneToMany(mappedBy = "player")
	private Set<EvaluationTalk> evaluationTalks;


	/**
	 * JPA required default constructor
	 */
	public Player() {

	}
	
	/**
	 * @param firstName first name of player
	 * @param lastName last name of player
	 * @param firstEmail email of player
	 * */
	public Player(String firstName, String lastName, String firstEmail) {
		this.firstName = firstEmail;
		this.lastName = lastName;
		this.firstEmail = firstEmail;
	}
	
	// TODO: Player constructors with parameters equals to input form (tbd)

	public int getID() {
		return ID;
	}

	public String getFirstEmail() {
		return firstEmail;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Role getType() {
		return type;
	}

	public void setType(Role type) {
		this.type = type;
	}

	public Draft getDraft() {
		return draft;
	}

	public void setDraft(Draft draft) {
		this.draft = draft;
	}

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}

	public String getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	public String getWorkingNumber() {
		return workingNumber;
	}

	public void setWorkingNumber(String workingNumber) {
		this.workingNumber = workingNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Contract getContract() {
		return contract;
	}

	public void setContract(Contract contract) {
		this.contract = contract;
	}

	public Country getCountryPermission() {
		return countryPermission;
	}

	public void setCountryPermission(Country countryPermission) {
		this.countryPermission = countryPermission;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Set<LineUp> getLineUps() {
		return lineUps;
	}

	public void setLineUps(Set<LineUp> lineUps) {
		this.lineUps = lineUps;
	}

	public Set<PlayerGameStats> getGameStats() {
		return gameStats;
	}

	public void setGameStats(Set<PlayerGameStats> gameStats) {
		this.gameStats = gameStats;
	}

	public Set<PerformanceDiagnostics> getPerformanceDiagnostics() {
		return performanceDiagnostics;
	}

	public void setPerformanceDiagnostics(Set<PerformanceDiagnostics> performanceDiagnostics) {
		this.performanceDiagnostics = performanceDiagnostics;
	}

	public Set<EvaluationTalk> getEvaluationTalks() {
		return evaluationTalks;
	}

	public void setEvaluationTalks(Set<EvaluationTalk> evaluationTalks) {
		this.evaluationTalks = evaluationTalks;
	}

	public void setFirstEmail(String firstEmail) {
		this.firstEmail = firstEmail;
	}
}
