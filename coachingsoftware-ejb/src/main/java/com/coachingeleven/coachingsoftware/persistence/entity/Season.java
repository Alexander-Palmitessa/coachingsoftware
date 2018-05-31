package com.coachingeleven.coachingsoftware.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

@Entity
@Table(name = "SEASON")
@NamedQueries({
	@NamedQuery(name = "findSeasonByTeamID",
			query = "SELECT s FROM Season s JOIN s.teams t WHERE t.ID = :teamId")
})
public class Season implements Serializable {
	
	private static final long serialVersionUID = 771259565903073462L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "SEASON_ID")
	private int ID;
	@Pattern(regexp = "^[a-zA-Z0-9\\s]+$", message = "{pattern.letter.number.space}")
	@NotNull
	private String name;
	@Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Calendar startDate;
	@Column(name = "ENDDATE")
    @Temporal(TemporalType.DATE)
    private Calendar endDate;
	@OneToMany(mappedBy = "season")
	private Set<Game> games;
	@OneToMany(mappedBy = "activeSeason")
	private Set<Team> activeTeams;
	@ManyToMany
	@JoinTable(
			name = "SEASON_TEAM",
			joinColumns = @JoinColumn(name = "SEASON_ID", referencedColumnName = "SEASON_ID"),
			inverseJoinColumns = @JoinColumn(name = "TEAM_ID", referencedColumnName = "TEAM_ID")
	)
	private Set<Team> teams;
	
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

	public Set<Game> getGames() {
		return games;
	}

	public void setGames(Set<Game> games) {
		this.games = games;
	}

	public Set<Team> getTeams() {
		return teams;
	}

	public void setTeams(Set<Team> teams) {
		this.teams = teams;
	}

	public Set<Team> getActiveTeams() {
		return activeTeams;
	}

	public void setActiveTeams(Set<Team> activeTeams) {
		this.activeTeams = activeTeams;
	}

}
