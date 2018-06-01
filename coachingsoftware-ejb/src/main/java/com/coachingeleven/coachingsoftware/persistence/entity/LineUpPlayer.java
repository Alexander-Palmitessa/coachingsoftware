package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.LineUpType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.MissingType;
import com.coachingeleven.coachingsoftware.persistence.enumeration.Position;

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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "LINEUP_PLAYERS")
public class LineUpPlayer implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LINEUP_PLAYER_ID")
    private int ID;
    @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "LINEUP_ID")
    private LineUp lineUp;
    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;
    @Column(name = "LINEUP_TYPE")
    @Enumerated(EnumType.STRING)
    private LineUpType lineUpType;
    @Column(name = "MISSING_TYPE")
    @Enumerated(EnumType.STRING)
    private MissingType missingType;
    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private Position position;

    /**
     * JPA required default constructor
     */
    public LineUpPlayer() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public LineUp getLineUp() {
        return lineUp;
    }

    public void setLineUp(LineUp lineUp) {
        this.lineUp = lineUp;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public LineUpType getLineUpType() {
        return lineUpType;
    }

    public void setLineUpType(LineUpType lineUpType) {
        this.lineUpType = lineUpType;
    }

    public MissingType getMissingType() {
        return missingType;
    }

    public void setMissingType(MissingType missingType) {
        this.missingType = missingType;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }
}
