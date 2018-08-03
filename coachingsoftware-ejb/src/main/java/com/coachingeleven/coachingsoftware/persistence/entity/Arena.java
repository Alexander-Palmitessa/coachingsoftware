/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "ARENA")
public class Arena implements Serializable {
	@Id
	@Column(name = "ARENA_ID")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int ID;
	@Column(name = "NAME", nullable = false)
	@NotNull(message = "{not.null}")
	@Pattern(regexp = "^[a-zA-Z0-9äöüÄÖÜéèêâà.\\s]+$", message = "{pattern.letter.number.space")
	private String name;
	@Embedded
	private Address address;

	/**
	 * Class constructor
	 * @param name the name of the arena
	 * @param address the Address of the arena
	 */
	public Arena(String name, Address address) {
		this.name = name;
		this.address = address;
	}

	/**
	 * JPA required default constructor
	 */
	public Arena(){

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

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
