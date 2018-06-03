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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Embeddable
public class Address implements Serializable {
	
	private static final long serialVersionUID = -1351895022038783357L;
	
	@Column(name = "CITY")
	@Pattern(regexp = "[a-zA-Z\\s]+$", message = "{pattern.letter.space}")
	private String city;
	@Column(name = "STREET")
	@Pattern(regexp = "^[a-zA-Z\\s]+$", message = "{pattern.letter.space}")
	private String street;
	@Column(name = "STREET_NR")
	@Pattern(regexp = "^[\\w\\s]+$", message = "{pattern.letter.number.space}")
	private String streetNr;
	@Column(name = "ZIP")
	@Min(value = 1000, message = "{number.zip.range}")
	@Max(value = 100000, message = "{number.zip.range}")
	private int zip;
	@ManyToOne
	@JoinColumn(name = "COUNTRY")
	private Country country;

	/**
	 * Class constructor
	 *
	 * @param city     the name of the city
	 * @param street   the name of the street
	 * @param streetNr the street number
	 * @param zip      the zip/postal code
	 * @param country  the country
	 */
	public Address(String city, String street, String streetNr, int zip, Country country) {
		this.city = city;
		this.street = street;
		this.streetNr = streetNr;
		this.zip = zip;
		this.country = country;
	}

	/**
	 * JPA required default constructor
	 */
	public Address() {

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

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}
}
