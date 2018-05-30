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
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class TrunkDiagnostic implements Serializable {

	@Column(name = "FRONT_TRUNK", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal frontTrunk;

	@Column(name = "SIDE_TRUNK", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal sideTrunk;

	@Column(name = "BACK_TRUNK", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal backTrunk;

	/**
	 * JPA required default constructor
	 */
	public TrunkDiagnostic() {

	}

	public BigDecimal getFrontTrunk() {
		return frontTrunk;
	}

	public void setFrontTrunk(BigDecimal frontTrunk) {
		this.frontTrunk = frontTrunk;
	}

	public BigDecimal getSideTrunk() {
		return sideTrunk;
	}

	public void setSideTrunk(BigDecimal sideTrunk) {
		this.sideTrunk = sideTrunk;
	}

	public BigDecimal getBackTrunk() {
		return backTrunk;
	}

	public void setBackTrunk(BigDecimal backTrunk) {
		this.backTrunk = backTrunk;
	}
}
