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
@Table(name = "PERF_DIAG")
public class PerformanceDiagnostics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERF_DIAG_ID")
	private int ID;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;

	/**
	 * JPA required default constructor
	 */
	public PerformanceDiagnostics(){

	}
}
