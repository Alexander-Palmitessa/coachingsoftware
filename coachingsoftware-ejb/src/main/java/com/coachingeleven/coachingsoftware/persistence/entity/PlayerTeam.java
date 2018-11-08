package com.coachingeleven.coachingsoftware.persistence.entity;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(PlayerTeamId.class)
@Table(name = "PLAYER_TEAM")
public class PlayerTeam {
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	private Team team;
	
	@Id
	@ManyToOne(fetch=FetchType.LAZY)
	private Player player;
	
	@Id
	@Column(name = "STARTDATE")
    @Temporal(TemporalType.DATE)
    private Calendar joinDate;
	
	@Column(name = "STARTDATE", updatable=false, insertable=false)
    @Temporal(TemporalType.DATE)
    private Calendar leaveDate;
	
	/**
	 * JPA required default constructor
	 */
	public PlayerTeam() {

	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Calendar getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Calendar joinDate) {
		this.joinDate = joinDate;
	}

	public Calendar getLeaveDate() {
		return leaveDate;
	}

	public void setLeaveDate(Calendar leaveDate) {
		this.leaveDate = leaveDate;
	}

}
