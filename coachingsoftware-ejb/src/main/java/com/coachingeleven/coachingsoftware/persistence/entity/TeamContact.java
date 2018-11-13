package com.coachingeleven.coachingsoftware.persistence.entity;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@IdClass(TeamContactId.class)
@Table(name = "TEAM_CONTACT")
@NamedQueries({
	@NamedQuery(name = "findTeamsByContact",
			query = "SELECT tc.team FROM TeamContact tc WHERE tc.contact.ID = :contactID")
})
public class TeamContact implements Serializable {
	
	private static final long serialVersionUID = 5086880701411757955L;

	@Id
	@JoinColumn(name = "TEAM_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Team team;
	
	@Id
	@JoinColumn(name = "CONTACT_ID")
	@ManyToOne(fetch=FetchType.LAZY)
	private Contact contact;
	
	@Id
	@Column(name = "JOINTDATE")
    @Temporal(TemporalType.DATE)
    private Calendar joinDate;
	
	@Column(name = "LEAVEDATE")
    @Temporal(TemporalType.DATE)
    private Calendar leaveDate;
	
	/**
	 * JPA required default constructor
	 */
	public TeamContact() {

	}
	
	public TeamContact(Team team, Contact contact, Calendar joinDate) {
		this.team = team;
		this.contact = contact;
		this.joinDate = joinDate;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
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

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}

}
