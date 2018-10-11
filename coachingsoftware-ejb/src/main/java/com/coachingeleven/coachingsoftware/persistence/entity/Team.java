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
				query = "SELECT t FROM Team t WHERE t.club.ID = :clubId"),
		@NamedQuery(name = "findTeamsBySeasonID",
				query = "SELECT t FROM Team t JOIN t.seasons s WHERE s.ID = :seasonID"),
		@NamedQuery(name = "findPreviousTeam",
				query = "SELECT t FROM Team t WHERE t.previousTeam.ID = :teamID")
})
public class Team implements Serializable {

	private static final long serialVersionUID = -2807580302598720350L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAM_ID")
	private int ID;
	@Column(name = "TEAM_NAME")
	@Pattern(regexp = "^[a-zA-Z0-9äöüÄÖÜéÉèÈàÀîÎâÂêÊôÔûÛ.\\s]+$", message = "{pattern.letter.number.space}")
	@NotNull
	private String name;
	@JoinColumn(name = "CLUB_ID")
	@ManyToOne
	private Club club;
	@ManyToMany
	@JoinTable(
			name = "TEAM_GAME",
			joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID"),
			inverseJoinColumns = @JoinColumn(name = "GAME_ID", referencedColumnName = "GAME_ID"))
	private Set<Game> games;
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
	@JoinColumn(name = "PREVIOUS_TEAM_ID")
	@OneToOne
	private Team previousTeam;
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
			name = "TEAM_CURRENT_PLAYERS",
			joinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID"),
			inverseJoinColumns = @JoinColumn(name = "PLAYER_ID", referencedColumnName = "PLAYER_ID")
	)
	private Set<Player> currentPlayers;
	private String teamPictureURL;
	private String teamLogoURL;
	@ManyToMany(mappedBy = "teams")
	private Set<Season> seasons;
	@ManyToMany(mappedBy = "historyTeams")
	private Set<Player> historyPlayers;
	@OneToMany(mappedBy = "team", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	private Set<TeamContact> teamContacts;


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

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
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

	public String getTeamPictureURL() {
		return teamPictureURL;
	}

	public void setTeamPictureURL(String teamPictureURL) {
		this.teamPictureURL = teamPictureURL;
	}

	public String getTeamLogoURL() {
		return teamLogoURL;
	}

	public void setTeamLogoURL(String teamLogoURL) {
		this.teamLogoURL = teamLogoURL;
	}

	public Team getPreviousTeam() {
		return previousTeam;
	}

	public void setPreviousTeam(Team previousTeam) {
		this.previousTeam = previousTeam;
	}

	public Set<Season> getSeasons() {
		return seasons;
	}

	public void setSeasons(Set<Season> seasons) {
		this.seasons = seasons;
	}

	public Set<Player> getCurrentPlayers() {
		return currentPlayers;
	}

	public void setCurrentPlayers(Set<Player> currentPlayers) {
		this.currentPlayers = currentPlayers;
	}

	public Set<Player> getHistoryPlayers() {
		return historyPlayers;
	}

	public void setHistoryPlayers(Set<Player> historyPlayers) {
		this.historyPlayers = historyPlayers;
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

	public Set<TeamContact> getTeamContacts() {
		return teamContacts;
	}

	public void setTeamContacts(Set<TeamContact> teamContacts) {
		this.teamContacts = teamContacts;
	}

	public void addTeamContact(TeamContact teamContact){
		teamContacts.add(teamContact);
	}
}
