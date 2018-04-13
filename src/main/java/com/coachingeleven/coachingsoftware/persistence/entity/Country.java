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
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
public class Country {

	@Id
	@Column(name = "COUNTRY_NAME")
	private String name;

	@OneToMany(mappedBy = "COUNTRY")
	private Set<Player> players;

	public Country(String name){
		this.name = name;
	}

	/**
	 * JPA required default constructor
	 */
	public Country(){
	}
}
