/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.ChangeOutType;

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

@Entity
@Table(name = "CHANGEOUT")
public class ChangeOut {
	@Id
	@Column(name = "CHANGEOUT_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "CHANGEOUT_TYPE")
	@Enumerated(EnumType.STRING)
	private ChangeOutType changeOutType;
	@Column(name = "MINUTE_OUT")
	private int minuteOut;
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
	 * @param changeOutType the enumerated type of the change out of the game i.e. RED, DOUBLEYELLOW, EXCHANGE
	 * @param minuteOut     the minute the player was taken from the game
	 * @param player        the player who was taken from the game
	 * @param game          the game which the change out happened
	 * @param comment       additional comment
	 */
	public ChangeOut(ChangeOutType changeOutType, int minuteOut, Player player, Game game, String comment) {
		this.changeOutType = changeOutType;
		this.minuteOut = minuteOut;
		this.player = player;
		this.game = game;
		this.comment = comment;
	}

	/**
	 * JPA required default constructor
	 */
	public ChangeOut() {

	}

	public int getID() {
		return ID;
	}

	public ChangeOutType getChangeOutType() {
		return changeOutType;
	}

	public void setChangeOutType(ChangeOutType changeOutType) {
		this.changeOutType = changeOutType;
	}

	public int getMinuteOut() {
		return minuteOut;
	}

	public void setMinuteOut(int minuteOut) {
		this.minuteOut = minuteOut;
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
