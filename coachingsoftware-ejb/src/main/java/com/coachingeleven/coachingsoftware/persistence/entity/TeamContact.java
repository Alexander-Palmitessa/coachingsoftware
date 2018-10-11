package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "TEAM_CONTACT")
@NamedQueries({
		@NamedQuery(name = "findTeamContact",
				query = "SELECT tc FROM TeamContact tc WHERE LOWER(tc.email) = LOWER(:email)")})
public class TeamContact implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "TEAMCONTACT_ID")
	private int ID;
	@Column(name = "FIRSTNAME", nullable = false)
	private String firstName;
	@Column(name = "LASTNAME", nullable = false)
	private String lastName;
	@Embedded
	private Address address;
	@Column(name = "ROLE")
	@Enumerated(value = EnumType.STRING)
	private Role role;
	@Column(name = "PRIVATE_NUMBER")
	@Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
	private String privateNumber;
	@Column(name = "WORKING_NUMBER")
	@Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
	private String workingNumber;
	@Column(name = "MOBILE_NUMBER")
	@Pattern(regexp = "^[0-9\\s]+$", message = "{pattern.number.space}")
	private String mobileNumber;
	@Column(name = "EMAIL", unique = true)
	@Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{pattern.email}")
	private String email;
	@ManyToOne
	@JoinColumn(name = "TEAM")
	private Team team;

	public TeamContact(){

	}

	public int getID() {
		return ID;
	}

	public void setID(int ID) {
		this.ID = ID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public String getPrivateNumber() {
		return privateNumber;
	}

	public void setPrivateNumber(String privateNumber) {
		this.privateNumber = privateNumber;
	}

	public String getWorkingNumber() {
		return workingNumber;
	}

	public void setWorkingNumber(String workingNumber) {
		this.workingNumber = workingNumber;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
