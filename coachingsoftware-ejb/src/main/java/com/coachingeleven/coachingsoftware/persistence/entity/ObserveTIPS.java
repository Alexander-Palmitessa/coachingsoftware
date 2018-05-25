package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.util.Calendar;

@Entity
@Table(name = "OBSERVE_TIPS")
public class ObserveTIPS {

    @Id
    @Column(name = "OBSERVE_TIPS_ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ID;

    @Column(name = "T")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 5, message = "{max.five}")
    private int technique;
    @Column(name = "T_COMMENT")
    private String techniqueComment;

    @Column(name = "I")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 5, message = "{max.five}")
    private int intelligence;
    @Column(name = "I_COMMENT")
    private String intelligenceComment;

    @Column(name = "P")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 5, message = "{max.five}")
    private int personality;
    @Column(name = "P_COMMENT")
    private String personalityComment;

    @Column(name = "S")
    @Min(value = 0, message = "{min.zero}")
    @Max(value = 5, message = "{max.five}")
    private int speed;
    @Column(name = "S_COMMENT")
    private String speedComment;

    @Column(name = "OBSERVED_AT_COMMENT")
    private String observedAtComment;

    @Column(name = "DATE")
    @Temporal(TemporalType.DATE)
    private Calendar date;

    @ManyToOne
    @JoinColumn(name = "PLAYER_ID")
    private Player player;

    //TODO: PLAYER -> SET OF OBSERVE TIPS

    /**
     * JPA required default constructor
     */
    public ObserveTIPS() {

    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public int getTechnique() {
        return technique;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

    public String getTechniqueComment() {
        return techniqueComment;
    }

    public void setTechniqueComment(String techniqueComment) {
        this.techniqueComment = techniqueComment;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public String getIntelligenceComment() {
        return intelligenceComment;
    }

    public void setIntelligenceComment(String intelligenceComment) {
        this.intelligenceComment = intelligenceComment;
    }

    public int getPersonality() {
        return personality;
    }

    public void setPersonality(int personality) {
        this.personality = personality;
    }

    public String getPersonalityComment() {
        return personalityComment;
    }

    public void setPersonalityComment(String personalityComment) {
        this.personalityComment = personalityComment;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getSpeedComment() {
        return speedComment;
    }

    public void setSpeedComment(String speedComment) {
        this.speedComment = speedComment;
    }

    public String getObservedAtComment() {
        return observedAtComment;
    }

    public void setObservedAtComment(String observedAtComment) {
        this.observedAtComment = observedAtComment;
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
}
