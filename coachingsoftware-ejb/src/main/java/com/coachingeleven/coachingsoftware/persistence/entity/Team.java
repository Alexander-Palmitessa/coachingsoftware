/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "TEAM")
@NamedQueries({
	@NamedQuery(name = "findTeam",
			query = "SELECT c FROM Team c WHERE LOWER(c.name) = LOWER(:teamname)")
})
public class Team implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_ID")
	private int ID;
	@Column(name = "TEAM_NAME")
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$")
	private String name;
	@JoinColumn(name = "CLUB_ID")
	@ManyToOne
	private Club club;
	@ManyToMany(mappedBy = "teams")
	private Set<Player> players;
	@ManyToMany
	private Set<Game> games;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="team")
	private UserAccount user;

	public Team(String name, Club club) {
		this.name = name;
		this.club = club;
	}

	/**
	 * JPA required default constructor
	 */
	public Team(){

	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Club getClub() {
		return club;
	}

	public void setClub(Club club) {
		this.club = club;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}
}
