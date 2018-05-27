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
import java.util.Set;

@Entity
@Table(name = "TEAM")
@NamedQueries({
        @NamedQuery(name = "findTeam",
                query = "SELECT c FROM Team c WHERE LOWER(c.name) = LOWER(:teamname)"),
        @NamedQuery(name = "findTeamsByClubId",
                query = "SELECT t FROM Team t WHERE t.club.ID = :clubId")
})
public class Team implements Serializable {

    private static final long serialVersionUID = -2807580302598720350L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAM_ID")
    private int ID;
    @Column(name = "TEAM_NAME")
    @Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "{pattern.letter.number.space}")
    @NotNull
    private String name;
    @JoinColumn(name = "CLUB_ID")
    @ManyToOne
    private Club club;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "TEAM_PLAYER",
            joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID"),
            inverseJoinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID"))
    private Set<Player> players;
    @OneToMany(mappedBy = "teamHome", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Game> gamesHome;
    @OneToMany(mappedBy = "teamAway", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Game> gamesAway;
    @OneToOne(mappedBy = "team")
    private UserAccount user;

    public Team(String name, Club club) {
        this.name = name;
        this.club = club;
    }

    /**
     * JPA required default constructor
     */
    public Team() {

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

    public Club getClub() {
        return club;
    }

    public void setClub(Club club) {
        this.club = club;
    }

    public Set<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Set<Player> players) {
        this.players = players;
    }

    public UserAccount getUser() {
        return user;
    }

    public void setUser(UserAccount user) {
        this.user = user;
    }

    public Set<Game> getGamesHome() {
        return gamesHome;
    }

    public void setGamesHome(Set<Game> gamesHome) {
        this.gamesHome = gamesHome;
    }

    public Set<Game> getGamesAway() {
        return gamesAway;
    }

    public void setGamesAway(Set<Game> gamesAway) {
        this.gamesAway = gamesAway;
    }
}
