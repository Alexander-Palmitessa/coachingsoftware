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
@Table(name = "CHANGEIN")
public class ChangeIn {
	@Id
	@Column(name = "CHANGEIN_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "MINUTE_IN")
	private int minuteIn;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;
	@ManyToOne
	@JoinColumn(name = "GAME ID")
	private Game game;
	@Column(name = "COMMENT")
	private String comment;

	/**
	 * Class constructor
	 *
	 * @param minuteIn the minute the player was taken in the game
	 * @param player   the player who taken in the game
	 * @param game     the game in which the change in happened
	 * @param comment  additional comment
	 */
	public ChangeIn(int minuteIn, Player player, Game game, String comment) {
		this.minuteIn = minuteIn;
		this.player = player;
		this.game = game;
		this.comment = comment;
	}

	/**
	 * JPA required default constructor
	 */
	public ChangeIn() {

	}

	public int getID() {
		return ID;
	}

	public int getMinuteIn() {
		return minuteIn;
	}

	public void setMinuteIn(int minuteIn) {
		this.minuteIn = minuteIn;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Game getGame() {
		return game;
	}

	public void setGame(Game game) {
		this.game = game;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
