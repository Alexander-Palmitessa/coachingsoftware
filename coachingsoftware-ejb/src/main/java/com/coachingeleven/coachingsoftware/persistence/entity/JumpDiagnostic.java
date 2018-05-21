/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.DecimalMin;
import java.math.BigDecimal;

@Embeddable
public class JumpDiagnostic {

	@Column(name = "SQUAT_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00")
	private BigDecimal squatJump;
	@Column(name = "COUNTER_MOVEMENT_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00")
	private BigDecimal counterMovementJump;
	@Column(name = "STANDING_LONG_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00")
	private BigDecimal standingLongJump;
	@Column(name = "DROP_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00")
	private BigDecimal dropJump;

	/**
	 * JPA required default constructor
	 */
	public JumpDiagnostic() {
	}

	/**
	 * Class constructor
	 *
	 * @param squatJump           distance for squad jumps
	 * @param counterMovementJump distance for counter movement jumps
	 * @param standingLongJump    distance for standing long jumps
	 * @param dropJump            distance for drop jumps
	 */
	public JumpDiagnostic(BigDecimal squatJump, BigDecimal counterMovementJump, BigDecimal standingLongJump, BigDecimal dropJump) {
		this.squatJump = squatJump;
		this.counterMovementJump = counterMovementJump;
		this.standingLongJump = standingLongJump;
		this.dropJump = dropJump;
	}

	public BigDecimal getSquatJump() {
		return squatJump;
	}

	public void setSquatJump(BigDecimal squatJump) {
		this.squatJump = squatJump;
	}

	public BigDecimal getCounterMovementJump() {
		return counterMovementJump;
	}

	public void setCounterMovementJump(BigDecimal counterMovementJump) {
		this.counterMovementJump = counterMovementJump;
	}

	public BigDecimal getStandingLongJump() {
		return standingLongJump;
	}

	public void setStandingLongJump(BigDecimal standingLongJump) {
		this.standingLongJump = standingLongJump;
	}

	public BigDecimal getDropJump() {
		return dropJump;
	}

	public void setDropJump(BigDecimal dropJump) {
		this.dropJump = dropJump;
	}
}
