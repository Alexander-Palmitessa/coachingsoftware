/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.math.BigDecimal;

@Entity
@Table(name = "PLAYER_GAMESTATS")
public class PlayerGameStats implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PLAYER_GAMESTATS_ID")
    int ID;
    @JoinColumn(name = "PLAYER", nullable = false)
    @ManyToOne
    private Player player;
    @JoinColumn(name = "GAME_ID", nullable = false)
    @ManyToOne
    private Game game;
    @Column(name = "MINUTES_PLAYED")
    @Min(value = 0, message = "{min.zero}")
    private int minutesPlayed;
    @Column(name = "TOTAL_DISTANCE")
    @Min(value = 0, message = "{min.zero}")
    private int totalDistance;
    @Column(name = "SPRINTS")
    @Min(value = 0, message = "{min.zero}")
    private int sprints;
    @Column(name = "ACCELERATIONS")
    @Min(value = 0, message = "{min.zero}")
    private int accelerations;
    @Column(name = "DECELERATIONS")
    @Min(value = 0, message = "{min.zero}")
    private int decelerations;
    @Column(name = "MAX_SPEED", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal maxSpeed;
    @Column(name = "AVG_SPEED", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal avgSpeed;
    @Column(name = "SPRINT_DISTANCE", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal sprintDistance;
    @Column(name = "BALL_CONTACTS")
    @Min(value = 0, message = "{min.zero}")
    private int ballContacts;
    @Column(name = "PASSES")
    @Min(value = 0, message = "{min.zero}")
    private int passes;
    @Column(name = "PASSES_PERCENTAGE")
    @Min(value = 0, message = "{min.zero}")
    private int passesPercentage;
    @Column(name = "DUELS")
    @Min(value = 0, message = "{min.zero}")
    private int duels;
    @Column(name = "DUELS_PERCENTAGE", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal duelPercentage;
    @Column(name = "OFF_DUELS")
    @Min(value = 0, message = "{min.zero}")
    private int offensiveDuels;
    @Column(name = "OFF_DUELS_PERCENTAGE", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal offensiveDuelsPercentage;
    @Column(name = "DEF_DUELS")
    @Min(value = 0, message = "{min.zero}")
    private int defensiveDuels;
    @Column(name = "DEF_DUELS_PERCENTAGE", precision = 7, scale = 2)
    @DecimalMin(value = "0.00", message = "{min.zero.decimal}")
    private BigDecimal defensiveDuelsPercentage;
    @Column(name = "GOALS")
    @Min(value = 0, message = "{min.zero}")
    private int goals;
    @Column(name = "ASSIST")
    @Min(value = 0, message = "{min.zero}")
    private int assist;
    @Column(name = "LOAD_INDICATOR")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 10, message = "{max.ten}")
    private int loadIndicator;
    @OneToOne
    @JoinColumn(name = "CHANGEIN_ID")
    private ChangeIn changeIn;
    @OneToOne
    @JoinColumn(name = "CHANGEOUT_ID")
    private ChangeOut changeOut;
    @Embedded
    private TIPS tips;
    @Column(name = "COMMENT")
    private String comment;

    /**
     * JPA required default constructor
     */
    public PlayerGameStats() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public int getMinutesPlayed() {
        return minutesPlayed;
    }

    public void setMinutesPlayed(int minutesPlayed) {
        this.minutesPlayed = minutesPlayed;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public int getSprints() {
        return sprints;
    }

    public void setSprints(int sprints) {
        this.sprints = sprints;
    }

    public int getAccelerations() {
        return accelerations;
    }

    public void setAccelerations(int accelerations) {
        this.accelerations = accelerations;
    }

    public int getDecelerations() {
        return decelerations;
    }

    public void setDecelerations(int decelerations) {
        this.decelerations = decelerations;
    }

    public BigDecimal getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(BigDecimal maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public BigDecimal getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(BigDecimal avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public BigDecimal getSprintDistance() {
        return sprintDistance;
    }

    public void setSprintDistance(BigDecimal sprintDistance) {
        this.sprintDistance = sprintDistance;
    }

    public int getBallContacts() {
        return ballContacts;
    }

    public void setBallContacts(int ballContacts) {
        this.ballContacts = ballContacts;
    }

    public int getPasses() {
        return passes;
    }

    public void setPasses(int passes) {
        this.passes = passes;
    }

    public int getPassesPercentage() {
        return passesPercentage;
    }

    public void setPassesPercentage(int passesPercentage) {
        this.passesPercentage = passesPercentage;
    }

    public int getDuels() {
        return duels;
    }

    public void setDuels(int duels) {
        this.duels = duels;
    }

    public BigDecimal getDuelPercentage() {
        return duelPercentage;
    }

    public void setDuelPercentage(BigDecimal duelPercentage) {
        this.duelPercentage = duelPercentage;
    }

    public int getOffensiveDuels() {
        return offensiveDuels;
    }

    public void setOffensiveDuels(int offensiveDuels) {
        this.offensiveDuels = offensiveDuels;
    }

    public BigDecimal getOffensiveDuelsPercentage() {
        return offensiveDuelsPercentage;
    }

    public void setOffensiveDuelsPercentage(BigDecimal offensiveDuelsPercentage) {
        this.offensiveDuelsPercentage = offensiveDuelsPercentage;
    }

    public int getDefensiveDuels() {
        return defensiveDuels;
    }

    public void setDefensiveDuels(int defensiveDuels) {
        this.defensiveDuels = defensiveDuels;
    }

    public BigDecimal getDefensiveDuelsPercentage() {
        return defensiveDuelsPercentage;
    }

    public void setDefensiveDuelsPercentage(BigDecimal defensiveDuelsPercentage) {
        this.defensiveDuelsPercentage = defensiveDuelsPercentage;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }

    public int getAssist() {
        return assist;
    }

    public void setAssist(int assist) {
        this.assist = assist;
    }

    public int getLoadIndicator() {
        return loadIndicator;
    }

    public void setLoadIndicator(int loadIndicator) {
        this.loadIndicator = loadIndicator;
    }

    public ChangeIn getChangeIn() {
        return changeIn;
    }

    public void setChangeIn(ChangeIn changeIn) {
        this.changeIn = changeIn;
    }

    public ChangeOut getChangeOut() {
        return changeOut;
    }

    public void setChangeOut(ChangeOut changeOut) {
        this.changeOut = changeOut;
    }

    public TIPS getTips() {
        return tips;
    }

    public void setTips(TIPS tips) {
        this.tips = tips;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }
}
