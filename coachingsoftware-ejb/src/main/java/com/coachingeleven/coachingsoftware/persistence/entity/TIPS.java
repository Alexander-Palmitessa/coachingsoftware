package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;

@Embeddable
public class TIPS implements Serializable {

	@Column(name = "TECHNIQUE")
	@Min(value = 0, message = "{min.zero}")
	@Max(value = 5, message = "{max.five}")
	private int technique;
	@Column(name = "INTELLIGENCE")
	@Min(value = 0, message = "{min.zero}")
	@Max(value = 5, message = "{max.five}")
	private int intelligence;
	@Column(name = "PERSONALITY")
	@Min(value = 0, message = "{min.zero}")
	@Max(value = 5, message = "{max.five}")
	private int personality;
	@Column(name = "SPEED")
	@Min(value = 0, message = "{min.zero}")
	@Max(value = 5, message = "{max.five}")
	private int speed;

	/**
	 * JPA required default constructor
	 */
	public TIPS() {

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

	public double getAverage() {
		double average = 0;
		int count = 0;
		int[] numbers = new int[]{intelligence, technique, personality, speed};
		for (int num : numbers) {
			if (num != 0) {
				average += technique;
				count++;
			}
		}
		return (double) Math.round(average / count * 100) / 100;
	}
}
