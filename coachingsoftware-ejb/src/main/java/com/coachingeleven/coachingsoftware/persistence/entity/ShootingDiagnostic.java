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
import javax.validation.constraints.Min;

@Embeddable
public class ShootingDiagnostic {

	@Column(name = "SHOT_LS_RF_1")
	@Min(value = 0)
	private int shotLsRf_1;
	@Column(name = "SHOT_LS_RF_2")
	@Min(value = 0)
	private int shotLsRf_2;
	@Column(name = "SHOT_LS_LF_1")
	@Min(value = 0)
	private int shotLsLf_1;
	@Column(name = "SHOT_LS_LF_2")
	@Min(value = 0)
	private int shotLsLf_2;
	@Column(name = "SHOT_RS_RF_1")
	@Min(value = 0)
	private int shotRsRf_1;
	@Column(name = "SHOT_RS_RF_2")
	@Min(value = 0)
	private int shotRsRf_2;
	@Column(name = "SHOT_RS_LF_1")
	@Min(value = 0)
	private int shotRsLf_1;
	@Column(name = "SHOT_RS_LF_2")
	@Min(value = 0)
	private int shotRsLf_2;

	/**
	 * JPA required default constructor
	 */
	public ShootingDiagnostic() {

	}

	/**
	 * Class constructor
	 *
	 * @param shotLsRf_1 score for shots in the left sector with the right foot 1st execution
	 * @param shotLsRf_2 score for shots in the left sector with the right foot 2nd execution
	 * @param shotLsLf_1 score for shots in the left sector with the left foot 1st execution
	 * @param shotLsLf_2 score for shots in the left sector with the left foot 2nd execution
	 * @param shotRsRf_1 score for shots in the right sector with the right foot 1st execution
	 * @param shotRsRf_2 score for shots in the right sector with the right foot 2nd execution
	 * @param shotRsLf_1 score for shots in the right sector with the left foot 1st execution
	 * @param shotRsLf_2 score for shots in the right sector with the left foot 2nd execution
	 */
	public ShootingDiagnostic(int shotLsRf_1, int shotLsRf_2, int shotLsLf_1, int shotLsLf_2, int shotRsRf_1,
							  int shotRsRf_2, int shotRsLf_1, int shotRsLf_2) {
		this.shotLsRf_1 = shotLsRf_1;
		this.shotLsRf_2 = shotLsRf_2;
		this.shotLsLf_1 = shotLsLf_1;
		this.shotLsLf_2 = shotLsLf_2;
		this.shotRsRf_1 = shotRsRf_1;
		this.shotRsRf_2 = shotRsRf_2;
		this.shotRsLf_1 = shotRsLf_1;
		this.shotRsLf_2 = shotRsLf_2;
	}

	public int getShotLsRf_1() {
		return shotLsRf_1;
	}

	public void setShotLsRf_1(int shotLsRf_1) {
		this.shotLsRf_1 = shotLsRf_1;
	}

	public int getShotLsRf_2() {
		return shotLsRf_2;
	}

	public void setShotLsRf_2(int shotLsRf_2) {
		this.shotLsRf_2 = shotLsRf_2;
	}

	public int getShotLsLf_1() {
		return shotLsLf_1;
	}

	public void setShotLsLf_1(int shotLsLf_1) {
		this.shotLsLf_1 = shotLsLf_1;
	}

	public int getShotLsLf_2() {
		return shotLsLf_2;
	}

	public void setShotLsLf_2(int shotLsLf_2) {
		this.shotLsLf_2 = shotLsLf_2;
	}

	public int getShotRsRf_1() {
		return shotRsRf_1;
	}

	public void setShotRsRf_1(int shotRsRf_1) {
		this.shotRsRf_1 = shotRsRf_1;
	}

	public int getShotRsRf_2() {
		return shotRsRf_2;
	}

	public void setShotRsRf_2(int shotRsRf_2) {
		this.shotRsRf_2 = shotRsRf_2;
	}

	public int getShotRsLf_1() {
		return shotRsLf_1;
	}

	public void setShotRsLf_1(int shotRsLf_1) {
		this.shotRsLf_1 = shotRsLf_1;
	}

	public int getShotRsLf_2() {
		return shotRsLf_2;
	}

	public void setShotRsLf_2(int shotRsLf_2) {
		this.shotRsLf_2 = shotRsLf_2;
	}
}
