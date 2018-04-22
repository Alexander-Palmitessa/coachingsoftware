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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class LineUp implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LINEUP_ID")
	private int ID;
	@ManyToMany(mappedBy = "lineUps")
	@JoinColumn(name = "STARTING_PLAYER_ID")
	private Set<Player> startingPlayers;
	@ManyToMany(mappedBy = "lineUps")
	@JoinColumn(name = "BENCHED_PLAYER_ID")
	private Set<Player> benchedPlayers;
	@Column(name = "SYSTEM")
	@Enumerated(EnumType.STRING)
	private System system;

	//TODO: INCOMPLETE

	/**
	 * JPA required default constructor
	 */
	public LineUp() {

	}
}
