/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Entity
@Table(name = "PERF_DIAG")
public class PerformanceDiagnostics implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERF_DIAG_ID")
	private int ID;
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID", nullable = false)
	private Player player;
	@Embedded
	private SpeedDiagnostic speedDiagnostic;
	@Embedded
	private JumpDiagnostic jumpDiagnostic;
	@Embedded
	private ShootingDiagnostic shootingDiagnostic;
	@Embedded
	private AdditionalDiagnostic additionalDiagnostic;


	/**
	 * Class constructor
	 *
	 * @param date                 the date of the performance diagnostics
	 * @param player               the player which was diagnosed
	 * @param speedDiagnostic      the speed diagnostics
	 * @param jumpDiagnostic       the jump diagnostic
	 * @param shootingDiagnostic   the shooting diagnostic
	 * @param additionalDiagnostic the additional diagnostics, i.e. mobility and muscles endurance
	 */
	public PerformanceDiagnostics(Calendar date, Player player, SpeedDiagnostic speedDiagnostic,
								  JumpDiagnostic jumpDiagnostic, ShootingDiagnostic shootingDiagnostic,
								  AdditionalDiagnostic additionalDiagnostic) {
		this.date = date;
		this.player = player;
		this.speedDiagnostic = speedDiagnostic;
		this.jumpDiagnostic = jumpDiagnostic;
		this.shootingDiagnostic = shootingDiagnostic;
		this.additionalDiagnostic = additionalDiagnostic;
	}

	/**
	 * JPA required default constructor
	 */
	public PerformanceDiagnostics() {

	}

	public int getID() {
		return ID;
	}


	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public SpeedDiagnostic getSpeedDiagnostic() {
		return speedDiagnostic;
	}

	public void setSpeedDiagnostic(SpeedDiagnostic speedDiagnostic) {
		this.speedDiagnostic = speedDiagnostic;
	}

	public JumpDiagnostic getJumpDiagnostic() {
		return jumpDiagnostic;
	}

	public void setJumpDiagnostic(JumpDiagnostic jumpDiagnostic) {
		this.jumpDiagnostic = jumpDiagnostic;
	}

	public ShootingDiagnostic getShootingDiagnostic() {
		return shootingDiagnostic;
	}

	public void setShootingDiagnostic(ShootingDiagnostic shootingDiagnostic) {
		this.shootingDiagnostic = shootingDiagnostic;
	}

	public AdditionalDiagnostic getAdditionalDiagnostic() {
		return additionalDiagnostic;
	}

	public void setAdditionalDiagnostic(AdditionalDiagnostic additionalDiagnostic) {
		this.additionalDiagnostic = additionalDiagnostic;
	}
}
