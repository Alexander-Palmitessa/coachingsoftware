/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
class Address {

	@Column(name = "CITY")
	private String city;
	@Column(name = "STREET")
	private String street;
	@Column(name = "STREET_NR")
	private String streetNr;
	@Column(name = "ZIP")
	private int zip;

	public Address(String city, String street, String streetNr, int zip) {
		this.city = city;
		this.street = street;
		this.streetNr = streetNr;
		this.zip = zip;
	}

	/**
	 * JPA required default constructor
	 */
	public Address(){

	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getStreet() {
		return street;
	}

	public void setStreet(String street) {
		this.street = street;
	}

	public String getStreetNr() {
		return streetNr;
	}

	public void setStreetNr(String streetNr) {
		this.streetNr = streetNr;
	}

	public int getZip() {
		return zip;
	}

	public void setZip(int zip) {
		this.zip = zip;
	}
}
