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
import javax.persistence.Table;

@Entity
@Table(name = "OBJECTIVE")
public class Objective {
	@Id
	@Column(name = "OBJECTIVE ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "OBJECTIVEL_NAME")
	private String name;
	@Column(name = "GOALS")
	private String goals;

	/**
	 * Class construcor
	 *
	 * @param name  the name of the objective
	 * @param goals the goals of the objecitve
	 */
	public Objective(String name, String goals) {
		this.name = name;
		this.goals = goals;
	}

	/**
	 * JPA required default constructor
	 */
	public Objective() {

	}

	public int getID() {
		return ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = goals;
	}
}
