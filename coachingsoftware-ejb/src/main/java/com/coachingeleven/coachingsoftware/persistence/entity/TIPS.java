package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Embeddable
public class TIPS {

    @Column(name = "TECHNIQUE")
    @Min(value = 0)
    @Max(value = 5)
    private int technique;
    @Column(name = "INTELLIGENCE")
    @Min(value = 0)
    @Max(value = 5)
    private int intelligence;
    @Column(name = "PERSONALITY")
    @Min(value = 0)
    @Max(value = 5)
    private int personality;
    @Column(name = "SPEED")
    @Min(value = 0)
    @Max(value = 5)
    private int speed;

    /**
     * JPA required default constructor
     */
    public TIPS(){

    }

    public int getTechnique() {
        return technique;
    }

    public void setTechnique(int technique) {
        this.technique = technique;
    }

    public int getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(int intelligence) {
        this.intelligence = intelligence;
    }

    public int getPersonality() {
        return personality;
    }

    public void setPersonality(int personality) {
        this.personality = personality;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
