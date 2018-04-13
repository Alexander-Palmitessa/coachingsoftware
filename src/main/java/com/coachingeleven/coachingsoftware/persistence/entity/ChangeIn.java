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

@Embeddable
public class ChangeIn {

	@Column(name = "CHANGE_IN")
	private boolean in;
	@Column(name = "MINUTE_IN")
	private int minuteIn;

	/**
	 * Class construcor
	 * @param in true if the player was changed in else false
	 * @param minuteIn the minute the player was changed in
	 */
	public ChangeIn(boolean in, int minuteIn) {
		this.in = in;
		this.minuteIn = minuteIn;
	}

	/**
	 * JPA required default constructor
	 */
	public ChangeIn(){

	}

	public boolean isIn() {
		return in;
	}

	public void setIn(boolean in) {
		this.in = in;
	}

	public int getMinuteIn() {
		return minuteIn;
	}

	public void setMinuteIn(int minuteIn) {
		this.minuteIn = minuteIn;
	}
}
