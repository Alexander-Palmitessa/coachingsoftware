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
import javax.persistence.Table;

@Entity
@Table(name = "SCOUTING_REPORT")
public class ScoutingReport {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SCOUTING_REPORT_ID")
	private int ID;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	//TODO: UNCOMPLETE

	/**
	 * JPA required default constructor
	 */
	public ScoutingReport(){

	}
}
