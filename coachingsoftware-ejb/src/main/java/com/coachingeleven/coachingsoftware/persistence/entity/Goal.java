/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.Foot;
import com.coachingeleven.coachingsoftware.persistence.enumeration.GoalType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Standard;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Zone;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Entity
@Table(name = "GOAL")
public class Goal implements Serializable {

	@Id
	@Column(name = "GOAL_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@JoinColumn(name = "SCORER_ID")
	@ManyToOne
	private Player scorer;
	@JoinColumn(name = "ASSISTANT_ID")
	@ManyToOne
	private Player assistant;
	@JoinColumn(name = "GAME_ID", nullable = false)
	@ManyToOne
	private Game game;
	@Enumerated(EnumType.STRING)
	@Column(name = "ZONE_SCORE")
	private Zone scoreZone;
	@Enumerated(EnumType.STRING)
	@Column(name = "ZONE_ASSIST")
	private Zone assistZone;
	@Enumerated(EnumType.STRING)
	@Column(name = "FOOT")
	private Foot foot;
	@Enumerated(EnumType.STRING)
	@Column(name = "STANDARD")
	private Standard standard;
	@Column(name = "MINUTE_SCORED")
	@Min(value = 0, message = "{min.zero}")
	private int minuteScored;
	@Enumerated(EnumType.STRING)
	private GoalType goalType;

	/**
	 * Class constructor for goals without assistant
	 *
	 * @param scorer       the goal scorer
	 * @param game         the game which the goal was scored in
	 * @param scoreZone    the zone where the goal was scored from
	 * @param foot         the foot or head the goal was scored with
	 * @param standard     NO if the goal wasn't scored from a standard else the standard
	 * @param minuteScored the minute the goal was scored
	 */
	public Goal(Player scorer, Game game, Zone scoreZone, Foot foot, Standard standard, int minuteScored) {
		this.scorer = scorer;
		this.game = game;
		this.scoreZone = scoreZone;
		this.foot = foot;
		this.standard = standard;
		this.minuteScored = minuteScored;
	}

	/**
	 * Class constructor for goals with assistant
	 *
	 * @param scorer       the goals scorer
	 * @param assistant    the goals assistant
	 * @param game         he game which the goal was scored in
	 * @param team         the team who scored the goal
	 * @param scoreZone    the zone where the goal was scored from
	 * @param assistZone   the zone where the goal was assisted from
	 * @param foot         the foot or head the goal was scored with
	 * @param standard     NO if the goal wasn't scored from a standard else the standard
	 * @param minuteScored the minute the goal was scored
	 */
	public Goal(Player scorer, Player assistant, Game game, Team team, Zone scoreZone, Zone assistZone, Foot foot,
				Standard standard, int minuteScored) {
		this.scorer = scorer;
		this.assistant = assistant;
		this.game = game;
		this.scoreZone = scoreZone;
		this.assistZone = assistZone;
		this.foot = foot;
		this.standard = standard;
		this.minuteScored = minuteScored;
	}

	/**
	 * JPA required default constructor
	 */
	public Goal() {

	}

	public int getID() {
		return ID;
	}

	public Player getScorer() {
		return scorer;
	}

	public void setScorer(Player scorer) {
		this.scorer = scorer;
	}

	public Player getAssistant() {
		return assistant;
	}

	public void setAssistant(Player assistant) {
		this.assistant = assistant;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public Zone getScoreZone() {
		return scoreZone;
	}

	public void setScoreZone(Zone scoreZone) {
		this.scoreZone = scoreZone;
	}

	public Zone getAssistZone() {
		return assistZone;
	}

	public void setAssistZone(Zone assistZone) {
		this.assistZone = assistZone;
	}

	public Foot getFoot() {
		return foot;
	}

	public void setFoot(Foot foot) {
		this.foot = foot;
	}

	public Standard getStandard() {
		return standard;
	}

	public void setStandard(Standard standard) {
		this.standard = standard;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public int getMinuteScored() {
		return minuteScored;
	}

	public void setMinuteScored(int minuteScored) {
		this.minuteScored = minuteScored;
	}

	public GoalType getGoalType() {
		return goalType;
	}

	public void setGoalType(GoalType goalType) {
		this.goalType = goalType;
	}
}
