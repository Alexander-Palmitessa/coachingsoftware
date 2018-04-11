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
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "TEAM")
public class Team {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_ID")
	private int ID;
	@Column(name = "TEAM_NAME")
	private String name;
	//TODO: ANNOTATE
	private Set<Player> players;



	public Team(){

	}
}
