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
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "COUNTRY")
@NamedQueries({
	@NamedQuery(name = "findCountry",
			query = "SELECT c FROM Country c WHERE LOWER(c.name) = LOWER(:countryname)")
})
public class Country implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "COUNTRY_ID")
	private int ID;

	@Column(name = "COUNTRY_NAME", nullable = false, unique = true)
	private String name;

	@OneToMany(mappedBy = "countryPermission")
	private Set<Player> players;

	/**
	 * Class constructor
	 * @param name the name of the country
	 */
	public Country(String name){
		this.name = name;
	}

	/**
	 * JPA required default constructor
	 */
	public Country(){
	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Player> getPlayers() {
		return players;
	}

	public void setPlayers(Set<Player> players) {
		this.players = players;
	}
}
