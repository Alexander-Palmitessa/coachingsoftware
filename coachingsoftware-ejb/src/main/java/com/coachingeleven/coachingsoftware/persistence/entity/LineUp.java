/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class LineUp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINEUP_ID")
    private int ID;
    @OneToMany(mappedBy = "lineUp", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<LineUpPlayer> lineUpPlayers;
    @Column(name = "SYSTEM")
    @Enumerated(EnumType.STRING)
    private System system;
    @JoinColumn(name = "GAME_ID")
    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "lineUp")
    private Game game;

    /**
     * JPA required default constructor
     */
    public LineUp() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public System getSystem() {
        return system;
    }

    public void setSystem(System system) {
        this.system = system;
    }

    public Set<LineUpPlayer> getLineUpPlayers() {
        return lineUpPlayers;
    }

    public void setLineUpPlayers(Set<LineUpPlayer> lineUpPlayers) {
        this.lineUpPlayers = lineUpPlayers;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
