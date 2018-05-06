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
public class AdditionalDiagnostic {
	@Column(name = "LEG_RAISE_L")
	private int legRaiseL;
	@Column(name = "LEG_RAISE_R")
	private int legRaiseR;
	@Column(name = "PUSH_UP")
	private int pushUp;
	@Column(name = "ROT_STABILITY_R")
	private int rotaryStabilityR;
	@Column(name = "ROT_STABILITY_L")
	private int rotaryStabilityL;
	@Column(name = "HURDLE_STEP_R")
	private int hurdleStepR;
	@Column(name = "HURDLE_STEP_L")
	private int hurdleStepL;
	@Column(name = "SHOULDER_MOB_R")
	private int shoulderMobilityR;
	@Column(name = "SHOULDER_MOB_L")
	private int shoulderMobilityL;
	@Column(name = "INLINE_LUNGE_R")
	private int inLineLungeR;
	@Column(name = "INLINE_LUNGE_L")
	private int inLineLungeL;

	/**
	 * JPA required default constructor
	 */
	public AdditionalDiagnostic() {

	}

	/**
	 * Class constructor
	 *
	 * @param legRaiseL         score for left leg raises
	 * @param legRaiseR         score for right leg raises
	 * @param pushUp            score for push ups
	 * @param rotaryStabilityR  score for right rotary stability
	 * @param rotaryStabilityL  score for left rotary stability
	 * @param hurdleStepR       score for right hurdle steps
	 * @param hurdleStepL       score for left hurdle steps
	 * @param shoulderMobilityR score for right shoulder mobility
	 * @param shoulderMobilityL score for left shoudler mobility
	 * @param inLineLungeR      score for right in-line lunges
	 * @param inLineLungeL      score for left in-line lunges
	 */
	public AdditionalDiagnostic(int legRaiseL, int legRaiseR, int pushUp, int rotaryStabilityR, int rotaryStabilityL,
								int hurdleStepR, int hurdleStepL, int shoulderMobilityR, int shoulderMobilityL,
								int inLineLungeR, int inLineLungeL) {
		this.legRaiseL = legRaiseL;
		this.legRaiseR = legRaiseR;
		this.pushUp = pushUp;
		this.rotaryStabilityR = rotaryStabilityR;
		this.rotaryStabilityL = rotaryStabilityL;
		this.hurdleStepR = hurdleStepR;
		this.hurdleStepL = hurdleStepL;
		this.shoulderMobilityR = shoulderMobilityR;
		this.shoulderMobilityL = shoulderMobilityL;
		this.inLineLungeR = inLineLungeR;
		this.inLineLungeL = inLineLungeL;
	}

	public int getLegRaiseL() {
		return legRaiseL;
	}

	public void setLegRaiseL(int legRaiseL) {
		this.legRaiseL = legRaiseL;
	}

	public int getLegRaiseR() {
		return legRaiseR;
	}

	public void setLegRaiseR(int legRaiseR) {
		this.legRaiseR = legRaiseR;
	}

	public int getPushUp() {
		return pushUp;
	}

	public void setPushUp(int pushUp) {
		this.pushUp = pushUp;
	}

	public int getRotaryStabilityR() {
		return rotaryStabilityR;
	}

	public void setRotaryStabilityR(int rotaryStabilityR) {
		this.rotaryStabilityR = rotaryStabilityR;
	}

	public int getRotaryStabilityL() {
		return rotaryStabilityL;
	}

	public void setRotaryStabilityL(int rotaryStabilityL) {
		this.rotaryStabilityL = rotaryStabilityL;
	}

	public int getHurdleStepR() {
		return hurdleStepR;
	}

	public void setHurdleStepR(int hurdleStepR) {
		this.hurdleStepR = hurdleStepR;
	}

	public int getHurdleStepL() {
		return hurdleStepL;
	}

	public void setHurdleStepL(int hurdleStepL) {
		this.hurdleStepL = hurdleStepL;
	}

	public int getShoulderMobilityR() {
		return shoulderMobilityR;
	}

	public void setShoulderMobilityR(int shoulderMobilityR) {
		this.shoulderMobilityR = shoulderMobilityR;
	}

	public int getShoulderMobilityL() {
		return shoulderMobilityL;
	}

	public void setShoulderMobilityL(int shoulderMobilityL) {
		this.shoulderMobilityL = shoulderMobilityL;
	}

	public int getInLineLungeR() {
		return inLineLungeR;
	}

	public void setInLineLungeR(int inLineLungeR) {
		this.inLineLungeR = inLineLungeR;
	}

	public int getInLineLungeL() {
		return inLineLungeL;
	}

	public void setInLineLungeL(int inLineLungeL) {
		this.inLineLungeL = inLineLungeL;
	}
}
