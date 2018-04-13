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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Calendar;

@Entity
@Table(name = "EVALUATION_TALK")
public class EvaluationTalk {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "EVALUATION_TALK_ID")
	private int ID;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID", nullable = false)
	private Player player;
	@Column(name = "DATE", nullable = false)
	@Temporal(TemporalType.DATE)
	private Calendar date;
	@Column(name = "SIT_ANALYSIS", nullable = false)
	private String situationalAnalysis;
	@Column(name = "GOALS", nullable = false)
	private String goals;
	@Column(name = "TIME_HORIZON", nullable = false)
	private String timeHorizon;

	/**
	 * Class constructor
	 * @param player the Player this EvaluationTalk belongs to
	 * @param date the date which the EvaluationTalk was held
	 * @param situationalAnalysis the situational analysis
	 * @param goals the goals which were set during the talk
	 * @param timeHorizon the time horizon which the goals should be reached
	 */
	public EvaluationTalk(Player player, Calendar date, String situationalAnalysis, String goals, String timeHorizon) {
		this.player = player;
		this.date = date;
		this.situationalAnalysis = situationalAnalysis;
		this.goals = goals;
		this.timeHorizon = timeHorizon;
	}

	/**
	 * JPA required default constructor
	 */
	public EvaluationTalk(){

	}

	public int getID() {
		return ID;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public String getSituationalAnalysis() {
		return situationalAnalysis;
	}

	public void setSituationalAnalysis(String situationalAnalysis) {
		this.situationalAnalysis = situationalAnalysis;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getTimeHorizon() {
		return timeHorizon;
	}

	public void setTimeHorizon(String timeHorizon) {
		this.timeHorizon = timeHorizon;
	}
}
