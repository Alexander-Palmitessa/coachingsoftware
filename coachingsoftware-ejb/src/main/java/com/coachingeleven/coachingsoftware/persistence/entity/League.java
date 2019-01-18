package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "LEAGUE")
public class League {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "LEAGUE_ID")
	private int ID;
	@Pattern(regexp = "^[a-zA-ZäöüÄÖÜéèêâà.\\s]+$", message = "{pattern.letter.space}")
	@NotNull
	private String name;
	
	/**
     * JPA required default constructor
     */
    public League() {}

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
