/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.System;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "LINEUP")
public class LineUp implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINEUP_ID")
    private int ID;
    @OneToMany(mappedBy = "lineUp", fetch = FetchType.EAGER)
    private Set<LineUpPlayer> lineUpPlayers;
    @Column(name = "SYSTEM")
    @Enumerated(EnumType.STRING)
    private System system;
    @OneToOne
    @JoinColumn(name = "GAME_ID", nullable = false)
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

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public Set<LineUpPlayer> getLineUpPlayers() {
        return lineUpPlayers;
    }

    public void setLineUpPlayers(Set<LineUpPlayer> lineUpPlayers) {
        this.lineUpPlayers = lineUpPlayers;
    }
}
