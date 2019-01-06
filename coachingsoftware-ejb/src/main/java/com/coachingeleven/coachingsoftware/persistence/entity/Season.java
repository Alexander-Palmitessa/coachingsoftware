package com.coachingeleven.coachingsoftware.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "SEASON")
public class Season implements Serializable {
	
	private static final long serialVersionUID = 771259565903073462L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEASON_ID")
	private int ID;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "{pattern.letter.number.space}")
	@NotNull
	@Column(name = "NAME")
	private String name;
	@Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
	@Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;
	
	/**
     * JPA required default constructor
     */
    public Season() {}
    
    public Season(String name, Calendar startDate, Calendar endDate) {
    	this.name = name;
    	this.startDate = startDate;
    	this.endDate = endDate;
    }

	public int getID() {
		return ID;
	}

	public void setID(int iD) {
		ID = iD;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
