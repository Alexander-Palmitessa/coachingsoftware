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
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal squatJump;

	@Column(name = "COUNTER_MOVEMENT_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal counterMovementJump;

	@Column(name = "DROP_JUMP", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal dropJump;

	@Column(name = "EXPL_POWER_ONE_LEG", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal explosivePowerOneLegged;

	/**
	 * JPA required default constructor
	 */
	public JumpDiagnostic() {
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

	public BigDecimal getDropJump() {
		return dropJump;
	}

	public void setDropJump(BigDecimal dropJump) {
		this.dropJump = dropJump;
	}

	public BigDecimal getExplosivePowerOneLegged() {
		return explosivePowerOneLegged;
	}

	public void setExplosivePowerOneLegged(BigDecimal explosivePowerOneLegged) {
		this.explosivePowerOneLegged = explosivePowerOneLegged;
	}
}
