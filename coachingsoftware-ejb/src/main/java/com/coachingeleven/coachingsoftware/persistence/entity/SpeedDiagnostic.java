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
public class SpeedDiagnostic {

	@Column(name = "TIME_10M", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal time10m;

	@Column(name = "TIME_20M", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal time20m;

	@Column(name = "TIME_30M", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal time30m;

	@Column(name = "TIME_40M", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal time40m;

	@Column(name = "TIME_STOP_GO", precision = 7, scale = 2)
	@DecimalMin(value = "0.00", message = "{min.zero.decimal}")
	private BigDecimal timeStopGo;

	/**
	 * JPA required default constructor
	 */
	public SpeedDiagnostic() {

	}

	public BigDecimal getTime10m() {
		return time10m;
	}

	public void setTime10m(BigDecimal time10m) {
		this.time10m = time10m;
	}

	public BigDecimal getTime20m() {
		return time20m;
	}

	public void setTime20m(BigDecimal time20m) {
		this.time20m = time20m;
	}

	public BigDecimal getTime30m() {
		return time30m;
	}

	public void setTime30m(BigDecimal time30m) {
		this.time30m = time30m;
	}

	public BigDecimal getTime40m() {
		return time40m;
	}

	public void setTime40m(BigDecimal time40m) {
		this.time40m = time40m;
	}

	public BigDecimal getTimeStopGo() {
		return timeStopGo;
	}

	public void setTimeStopGo(BigDecimal timeStopGo) {
		this.timeStopGo = timeStopGo;
	}
}
