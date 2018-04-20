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
import java.math.BigDecimal;

@Embeddable
public class SpeedDiagnostic {
	@Column(name = "10M_1", precision = 7, scale = 2)
	private BigDecimal time10m_1;
	@Column(name = "10M_2", precision = 7, scale = 2)
	private BigDecimal time10m_2;
	@Column(name = "20M_1", precision = 7, scale = 2)
	private BigDecimal time20m_1;
	@Column(name = "20M_2", precision = 7, scale = 2)
	private BigDecimal time20m_2;
	@Column(name = "AGILITY_1", precision = 7, scale = 2)
	private BigDecimal timeAgility_1;
	@Column(name = "AGILITY_2", precision = 7, scale = 2)
	private BigDecimal timeAgility_2;
	@Column(name = "DRIBBLING_1", precision = 7, scale = 2)
	private BigDecimal timeDribbling_1;
	@Column(name = "DRIBBLING_2", precision = 7, scale = 2)
	private BigDecimal timeDribbling_2;
	@Column(name = "BALL_CNTRL_1", precision = 7, scale = 2)
	private BigDecimal timeBallCntrl_1;
	@Column(name = "BALL_CNTRL_2", precision = 7, scale = 2)
	private BigDecimal timeBallCntrl_2;

	/**
	 * JPA required default constructor
	 */
	public SpeedDiagnostic(){

	}

	/**
	 * Class constructor
	 *
	 * @param time10m_1       seconds for the 10m sprint 1st execution
	 * @param time10m_2       seconds for the 10m sprint 2nd execution
	 * @param time20m_1       seconds for the 20m sprint 1st execution
	 * @param time20m_2       seconds for the 20m sprint 2nd execution
	 * @param timeAgility_1   seconds for the sprint agility 1st execution
	 * @param timeAgility_2   seconds for the sprint agility 2nd execution
	 * @param timeDribbling_1 seconds for dribbling 1st execution
	 * @param timeDribbling_2 seconds for dribbling 2nd execution
	 * @param timeBallCntrl_1 seconds for ball control 1st execution
	 * @param timeBallCntrl_2 seconds for ball control 2nd execution
	 */
	public SpeedDiagnostic(BigDecimal time10m_1, BigDecimal time10m_2, BigDecimal time20m_1, BigDecimal time20m_2,
						   BigDecimal timeAgility_1, BigDecimal timeAgility_2, BigDecimal timeDribbling_1,
						   BigDecimal timeDribbling_2, BigDecimal timeBallCntrl_1, BigDecimal timeBallCntrl_2) {
		this.time10m_1 = time10m_1;
		this.time10m_2 = time10m_2;
		this.time20m_1 = time20m_1;
		this.time20m_2 = time20m_2;
		this.timeAgility_1 = timeAgility_1;
		this.timeAgility_2 = timeAgility_2;
		this.timeDribbling_1 = timeDribbling_1;
		this.timeDribbling_2 = timeDribbling_2;
		this.timeBallCntrl_1 = timeBallCntrl_1;
		this.timeBallCntrl_2 = timeBallCntrl_2;
	}

	public BigDecimal getTime10m_1() {
		return time10m_1;
	}

	public void setTime10m_1(BigDecimal time10m_1) {
		this.time10m_1 = time10m_1;
	}

	public BigDecimal getTime10m_2() {
		return time10m_2;
	}

	public void setTime10m_2(BigDecimal time10m_2) {
		this.time10m_2 = time10m_2;
	}

	public BigDecimal getTime20m_1() {
		return time20m_1;
	}

	public void setTime20m_1(BigDecimal time20m_1) {
		this.time20m_1 = time20m_1;
	}

	public BigDecimal getTime20m_2() {
		return time20m_2;
	}

	public void setTime20m_2(BigDecimal time20m_2) {
		this.time20m_2 = time20m_2;
	}

	public BigDecimal getTimeAgility_1() {
		return timeAgility_1;
	}

	public void setTimeAgility_1(BigDecimal timeAgility_1) {
		this.timeAgility_1 = timeAgility_1;
	}

	public BigDecimal getTimeAgility_2() {
		return timeAgility_2;
	}

	public void setTimeAgility_2(BigDecimal timeAgility_2) {
		this.timeAgility_2 = timeAgility_2;
	}

	public BigDecimal getTimeDribbling_1() {
		return timeDribbling_1;
	}

	public void setTimeDribbling_1(BigDecimal timeDribbling_1) {
		this.timeDribbling_1 = timeDribbling_1;
	}

	public BigDecimal getTimeDribbling_2() {
		return timeDribbling_2;
	}

	public void setTimeDribbling_2(BigDecimal timeDribbling_2) {
		this.timeDribbling_2 = timeDribbling_2;
	}

	public BigDecimal getTimeBallCntrl_1() {
		return timeBallCntrl_1;
	}

	public void setTimeBallCntrl_1(BigDecimal timeBallCntrl_1) {
		this.timeBallCntrl_1 = timeBallCntrl_1;
	}

	public BigDecimal getTimeBallCntrl_2() {
		return timeBallCntrl_2;
	}

	public void setTimeBallCntrl_2(BigDecimal timeBallCntrl_2) {
		this.timeBallCntrl_2 = timeBallCntrl_2;
	}
}
