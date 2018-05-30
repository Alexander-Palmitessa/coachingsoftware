/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRE_GAME_REPORT")
public class PreGameReport implements Serializable {

	@Id
	@Column(name = "PRE_GAME_REPORT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@OneToOne
	@JoinColumn(name = "GAME_ID", nullable = false)
	private Game game;
	@Column(name = "GOALS")
	private String goals;
	@Column(name = "W_BALL")
	private String withBall;
	@Column(name = "WO_BALL")
	private String withoutBall;
	@Column(name = "LOSE_BALL")
	private String loseBall;
	@Column(name = "TAKE_BALL")
	private String takeBall;

	/**
	 * Class constructor
	 *
	 * @param game        the game of the gamereport
	 * @param goals       the goals set for this game
	 * @param withBall    goals in the game with the ball
	 * @param withoutBall goals in the game without the ball
	 * @param loseBall    goals in the game losing the ball
	 * @param takeBall    goals in the game taking the ball away
	 */
	public PreGameReport(Game game, String goals, String withBall, String withoutBall,
						 String loseBall, String takeBall) {
		this.game = game;
		this.goals = goals;
		this.withBall = withBall;
		this.withoutBall = withoutBall;
		this.loseBall = loseBall;
		this.takeBall = takeBall;
	}

	/**
	 * JPA required default constructor
	 */
	public PreGameReport() {

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}

	public String getWithBall() {
		return withBall;
	}

	public void setWithBall(String positiveWithBall) {
		this.withBall = positiveWithBall;
	}

	public String getWithoutBall() {
		return withoutBall;
	}

	public void setWithoutBall(String positiveWithoutBall) {
		this.withoutBall = positiveWithoutBall;
	}

	public String getLoseBall() {
		return loseBall;
	}

	public void setLoseBall(String positivLoseBall) {
		this.loseBall = positivLoseBall;
	}

	public String getTakeBall() {
		return takeBall;
	}

	public void setTakeBall(String positiveTakeBall) {
		this.takeBall = positiveTakeBall;
	}

}
