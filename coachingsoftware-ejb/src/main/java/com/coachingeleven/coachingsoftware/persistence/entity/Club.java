/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "CLUB")
@NamedQueries({
        @NamedQuery(name = "findClub",
                query = "SELECT c FROM Club c WHERE LOWER(c.name) = LOWER(:clubname)")
})
public class Club implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CLUB_ID")
    private int ID;
    @Column(name = "CLUB_NAME", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "{pattern.letter.number.space}")
    @NotNull(message = "{not.null}")
    private String name;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "club", orphanRemoval = true)
    private Set<Team> teams;
    @Embedded
	private Address address;

    /**
     * Class constructor
     *
     * @param name the name of the team
     */
    public Club(String name) {
        this.name = name;
        teams = new HashSet<>();
    }

    /**
     * JPA required default constructor
     */
    public Club() {

    }

    public int getID() {
        return ID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Set<Team> getTeams() {
        return teams;
    }

    public void setTeams(Set<Team> teams) {
        this.teams = teams;
    }

    public void addTeam(Team team) {
        teams.add(team);
        team.setClub(this);
    }

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}
}
