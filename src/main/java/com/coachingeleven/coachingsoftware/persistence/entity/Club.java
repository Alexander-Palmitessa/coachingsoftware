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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "CLUB")
public class Club {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CLUB_ID")
	private int ID;
	@Column(name = "CLUB_NAME", nullable = false)
	private String name;
	@OneToMany
	@Column(name = "TEAMS")
	private Set<Team> teams;

	/**
	 * Class constructor
	 *
	 * @param name the name of the team
	 */
	public Club(String name) {
		this.name = name;
	}

	/**
	 * JPA required default constructor
	 */
	public Club() {

	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

}
