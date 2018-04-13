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
import java.util.Calendar;

@Entity
@Table(name = "EVALUATION_TALK")
public class EvaluationTalk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVALUATION_TALK_ID")
	private int ID;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;
	@Column(name = "DATUM")
	private Calendar date;

	/**
	 * JPA required default constructor
	 */
	public EvaluationTalk(){

	}
}
