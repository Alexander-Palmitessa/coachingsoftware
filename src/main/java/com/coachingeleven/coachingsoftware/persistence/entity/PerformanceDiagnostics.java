/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Calendar;

@Entity
@Table(name = "PERF_DIAG")
public class PerformanceDiagnostics {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "PERF_DIAG_ID")
	private int ID;
	@Column(name = "DATE")
	@Temporal(TemporalType.DATE)
	private Calendar date;
	@ManyToOne
	@JoinColumn(name = "PLAYER_ID")
	private Player player;
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
	@Column(name = "SHOT_LS_RF_1")
	private int shotLsRf_1;
	@Column(name = "SHOT_LS_RF_2")
	private int shotLsRf_2;
	@Column(name = "SHOT_LS_LF_1")
	private int shotLsLf_1;
	@Column(name = "SHOT_LS_LF_2")
	private int shotLsLf_2;
	@Column(name = "SHOT_RS_RF_1")
	private int shotRsRf_1;
	@Column(name = "SHOT_RS_RF_2")
	private int shotRsRf_2;
	@Column(name = "SHOT_RS_LF_1")
	private int shotRsLf_1;
	@Column(name = "SHOT_RS_LF_2")
	private int shotRsLf_2;
	@Column(name = "SQUAT_JUMP", precision = 7, scale = 2)
	private BigDecimal squatJump;
	@Column(name = "COUNTER_MOVEMENT_JUMP", precision = 7, scale = 2)
	private BigDecimal counterMovementJump;
	@Column(name = "STANDING_LONG_JUMP", precision = 7, scale = 2)
	private BigDecimal standingLongJump;
	@Column(name = "DROP_JUMP", precision = 7, scale = 2)
	private BigDecimal dropJump;
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
	 * @param date                the date of the perfomance diagnostic
	 * @param player              the player whose performance was measured
	 * @param time10m_1           seconds for the 10m sprint 1st execution
	 * @param time10m_2           seconds for the 10m sprint 2nd execution
	 * @param time20m_1           seconds for the 20m sprint 1st execution
	 * @param time20m_2           seconds for the 20m sprint 2nd execution
	 * @param timeAgility_1       seconds for the sprint agility 1st execution
	 * @param timeAgility_2       seconds for the sprint agility 2nd execution
	 * @param timeDribbling_1     seconds for dribbling 1st execution
	 * @param timeDribbling_2     seconds for dribbling 2nd execution
	 * @param timeBallCntrl_1     seconds for ball control 1st execution
	 * @param timeBallCntrl_2     seconds for ball control 2nd execution
	 * @param shotLsRf_1          score for shots in the left sector with the right foot 1st execution
	 * @param shotLsRf_2          score for shots in the left sector with the right foot 2nd execution
	 * @param shotLsLf_1          score for shots in the left sector with the left foot 1st execution
	 * @param shotLsLf_2          score for shots in the left sector with the left foot 2nd execution
	 * @param shotRsRf_1          score for shots in the right sector with the right foot 1st execution
	 * @param shotRsRf_2          score for shots in the right sector with the right foot 2nd execution
	 * @param shotRsLf_1          score for shots in the right sector with the left foot 1st execution
	 * @param shotRsLf_2          score for shots in the right sector with the left foot 2nd execution
	 * @param squatJump           distance for squad jumps
	 * @param counterMovementJump distance for counter movement jumps
	 * @param standingLongJump    distance for standing long jumps
	 * @param dropJump            distance for drop jumps
	 * @param legRaiseL           score for left leg raises
	 * @param legRaiseR           score for right leg raises
	 * @param pushUp              score for push ups
	 * @param rotaryStabilityR    score for right rotary stability
	 * @param rotaryStabilityL    score for left rotary stability
	 * @param hurdleStepR         score for right hurdle steps
	 * @param hurdleStepL         score for left hurdle steps
	 * @param shoulderMobilityR   score for right shoulder mobility
	 * @param shoulderMobilityL   score for left shoudler mobility
	 * @param inLineLungeR        score for right in-line lunges
	 * @param inLineLungeL        score for left in-line lunges
	 */
	public PerformanceDiagnostics(Calendar date, Player player, BigDecimal time10m_1, BigDecimal time10m_2,
								  BigDecimal time20m_1, BigDecimal time20m_2, BigDecimal timeAgility_1,
								  BigDecimal timeAgility_2, BigDecimal timeDribbling_1, BigDecimal timeDribbling_2,
								  BigDecimal timeBallCntrl_1, BigDecimal timeBallCntrl_2, int shotLsRf_1,
								  int shotLsRf_2, int shotLsLf_1, int shotLsLf_2, int shotRsRf_1, int shotRsRf_2,
								  int shotRsLf_1, int shotRsLf_2, BigDecimal squatJump, BigDecimal counterMovementJump,
								  BigDecimal standingLongJump, BigDecimal dropJump, int legRaiseL, int legRaiseR,
								  int pushUp, int rotaryStabilityR, int rotaryStabilityL, int hurdleStepR,
								  int hurdleStepL, int shoulderMobilityR, int shoulderMobilityL, int inLineLungeR,
								  int inLineLungeL) {
		this.date = date;
		this.player = player;
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
		this.shotLsRf_1 = shotLsRf_1;
		this.shotLsRf_2 = shotLsRf_2;
		this.shotLsLf_1 = shotLsLf_1;
		this.shotLsLf_2 = shotLsLf_2;
		this.shotRsRf_1 = shotRsRf_1;
		this.shotRsRf_2 = shotRsRf_2;
		this.shotRsLf_1 = shotRsLf_1;
		this.shotRsLf_2 = shotRsLf_2;
		this.squatJump = squatJump;
		this.counterMovementJump = counterMovementJump;
		this.standingLongJump = standingLongJump;
		this.dropJump = dropJump;
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

	/**
	 * JPA required default constructor
	 */
	public PerformanceDiagnostics() {

	}

	public int getID() {
		return ID;
	}

	public Calendar getDate() {
		return date;
	}

	public void setDate(Calendar date) {
		this.date = date;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
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
