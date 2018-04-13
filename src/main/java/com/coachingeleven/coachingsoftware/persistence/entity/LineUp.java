/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class LineUp {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LINEUP_ID")
	private int ID;
	@ManyToMany(mappedBy = "lineUps")
	@Column(name = "STARTING_PLAYER")
	private Set<Player> startingPlayers;
	@ManyToMany(mappedBy = "lineUps")
	@Column(name = "BENCHED_PLAYER")
	private Set<Player> benchedPlayers;
	@Column(name = "SYSTEM")
	@Enumerated(EnumType.STRING)
	private System system;

	//TODO: UNCOMPLETE

	/**
	 * JPA required default constructor
	 */
	public LineUp() {

	}
}
