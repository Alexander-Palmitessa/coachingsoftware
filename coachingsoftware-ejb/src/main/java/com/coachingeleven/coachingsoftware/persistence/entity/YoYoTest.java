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
import java.io.Serializable;
import java.math.BigDecimal;

@Embeddable
public class YoYoTest implements Serializable {

	@Column(name = "LEVEL", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal level;

	@Column(name = "METERS", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal meters;

	@Column(name = "VMA", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal vma;


	/**
	 * JPA required default constructor
	 */
	public YoYoTest() {

	}

	public BigDecimal getLevel() {
		return level;
	}

	public void setLevel(BigDecimal level) {
		this.level = level;
	}

	public BigDecimal getMeters() {
		return meters;
	}

	public void setMeters(BigDecimal meters) {
		this.meters = meters;
	}

	public BigDecimal getVma() {
		return vma;
	}

	public void setVma(BigDecimal vma) {
		this.vma = vma;
	}
}
