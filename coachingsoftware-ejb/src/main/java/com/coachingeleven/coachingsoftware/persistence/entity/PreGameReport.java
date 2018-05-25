/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "PRE_GAME_REPORT")
public class PreGameReport implements Serializable {

    @Id
    @Column(name = "PRE_GAME_REPORT_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;
    @OneToOne
    @JoinColumn(name = "GAME_ID", nullable = false)
    private Game game;
    @Column(name = "GOALS")
    private String goals;
    @Column(name = "POS_W_BALL")
    private String positiveWithBall;
    @Column(name = "NEG_W_BALL")
    private String negativeWithBall;
    @Column(name = "POS_WO_BALL")
    private String positiveWithoutBall;
    @Column(name = "NEG_WO_BALL")
    private String negativeWithoutBall;
    @Column(name = "POS_LOSE_BALL")
    private String positiveLoseBall;
    @Column(name = "NEG_LOSE_BALL")
    private String negativeLoseBall;
    @Column(name = "POS_TAKE_BALL")
    private String positiveTakeBall;
    @Column(name = "NEG_TAKE_BALL")
    private String negativeTakeBall;

    /**
     * Class constructor
     *
     * @param game                the game of the gamereport
     * @param goals               the goals set for this game
     * @param positiveWithBall    positives in the game with the ball
     * @param negativeWithBall    negatives in the game wit the ball
     * @param positiveWithoutBall positives in the game without the ball
     * @param negativeWithoutBall negatives in the game without the ball
     * @param positiveLoseBall    positives in the game losing the ball
     * @param negativeLoseBall    negatives in the game losing the ball
     * @param positiveTakeBall    positives in the game taking the ball away
     * @param negativeTakeBall    negativess in the game taking the ball away
     */
    public PreGameReport(Game game, String goals, String positiveWithBall, String negativeWithBall, String positiveWithoutBall,
                         String negativeWithoutBall, String positiveLoseBall, String negativeLoseBall,
                         String positiveTakeBall, String negativeTakeBall) {
        this.game = game;
        this.goals = goals;
        this.positiveWithBall = positiveWithBall;
        this.negativeWithBall = negativeWithBall;
        this.positiveWithoutBall = positiveWithoutBall;
        this.negativeWithoutBall = negativeWithoutBall;
        this.positiveLoseBall = positiveLoseBall;
        this.negativeLoseBall = negativeLoseBall;
        this.positiveTakeBall = positiveTakeBall;
        this.negativeTakeBall = negativeTakeBall;
    }

    /**
     * JPA required default constructor
     */
    public PreGameReport() {

    }

    public int getID() {
        return ID;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public String getPositiveWithBall() {
        return positiveWithBall;
    }

    public void setPositiveWithBall(String positiveWithBall) {
        this.positiveWithBall = positiveWithBall;
    }

    public String getNegativeWithBall() {
        return negativeWithBall;
    }

    public void setNegativeWithBall(String negativeWithBall) {
        this.negativeWithBall = negativeWithBall;
    }

    public String getPositiveWithoutBall() {
        return positiveWithoutBall;
    }

    public void setPositiveWithoutBall(String positiveWithoutBall) {
        this.positiveWithoutBall = positiveWithoutBall;
    }

    public String getNegativeWithoutBall() {
        return negativeWithoutBall;
    }

    public void setNegativeWithoutBall(String negativeWithoutBall) {
        this.negativeWithoutBall = negativeWithoutBall;
    }

    public String getPositiveLoseBall() {
        return positiveLoseBall;
    }

    public void setPositiveLoseBall(String positivLoseBall) {
        this.positiveLoseBall = positivLoseBall;
    }

    public String getNegativeLoseBall() {
        return negativeLoseBall;
    }

    public void setNegativeLoseBall(String negativeLoseBall) {
        this.negativeLoseBall = negativeLoseBall;
    }

    public String getPositiveTakeBall() {
        return positiveTakeBall;
    }

    public void setPositiveTakeBall(String positiveTakeBall) {
        this.positiveTakeBall = positiveTakeBall;
    }

    public String getNegativeTakeBall() {
        return negativeTakeBall;
    }

    public void setNegativeTakeBall(String negativeTakeBall) {
        this.negativeTakeBall = negativeTakeBall;
    }
}
