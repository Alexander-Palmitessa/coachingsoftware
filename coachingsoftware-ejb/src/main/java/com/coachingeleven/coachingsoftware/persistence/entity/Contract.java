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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.io.Serializable;
import java.util.Calendar;

@Embeddable
public class Contract implements Serializable {
	@Column(name = "CONTRACT_START")
	@Temporal(TemporalType.DATE)
	private Calendar start;
	@Column(name = "CONTRACT_END")
	@Temporal(TemporalType.DATE)
	private Calendar end;
	@Column(name = "CONTRACT_COMMENT")
	private String comment;

	/**
	 * @param start   the date of the start of the contract
	 * @param end     the date of the end of the contract
	 * @param comment additional comments to the contract
	 */
	public Contract(Calendar start, Calendar end, String comment) {
		this.start = start;
		this.end = end;
		this.comment = comment;
	}

	/**
	 * JPA required default constructor
	 */
	public Contract() {

	}

	public Calendar getStart() {
		return start;
	}

	public void setStart(Calendar start) {
		this.start = start;
	}

	public Calendar getEnd() {
		return end;
	}

	public void setEnd(Calendar end) {
		this.end = end;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
}
