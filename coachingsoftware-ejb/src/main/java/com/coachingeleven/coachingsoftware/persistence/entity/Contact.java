package com.coachingeleven.coachingsoftware.persistence.entity;

import com.coachingeleven.coachingsoftware.persistence.enumeration.Role;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

@Entity
@Table(name = "CONTACT")
@NamedQueries({
		@NamedQuery(name = "findContactByEmail",
				query = "SELECT c FROM Contact c WHERE LOWER(c.email) = LOWER(:email)")})
public class Contact implements Serializable {

	private static final long serialVersionUID = -7440605309484763232L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "CONTACT_ID")
	private int ID;
	@Column(name = "FIRST_NAME", nullable = false)
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜéÉèÈàÀîÎâÂêÊôÔûÛ\\s]+$", message = "{pattern.letter.space}")
    @NotNull
    private String firstName;
    @Column(name = "LAST_NAME", nullable = false)
    @Pattern(regexp = "^[a-zA-ZäöüÄÖÜéÉèÈàÀîÎâÂêÊôÔûÛ\\s]+$", message = "{pattern.letter.space}")
    @NotNull
    private String lastName;
	@Embedded
	private Address address;
	@Column(name = "ROLE", nullable = false)
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
	@Column(name = "BIRTHDATE")
    @Temporal(TemporalType.DATE)
    private Calendar birthdate;
	@Column(name = "AVATARURL")
	private String avatarUrl;
	@OneToOne(fetch=FetchType.LAZY, mappedBy="contact")
	@JoinColumn(name = "USERACCOUNT", unique = true)
	private UserAccount userAccount;
	@OneToMany(mappedBy = "contact", fetch=FetchType.LAZY)
	private List<TeamContact> teamContacts;

	public Contact(){

	}
	
	public Contact(String firstname, String lastname) {
		this.firstName = firstname;
		this.lastName = lastname;
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

	public Calendar getBirthdate() {
		return birthdate;
	}

	public void setBirthdate(Calendar birthdate) {
		this.birthdate = birthdate;
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}

	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}

	public List<TeamContact> getTeamContacts() {
		return teamContacts;
	}

	public void setTeamContacts(List<TeamContact> teamContacts) {
		this.teamContacts = teamContacts;
	}
}
