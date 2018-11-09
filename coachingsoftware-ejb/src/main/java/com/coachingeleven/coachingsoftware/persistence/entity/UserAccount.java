package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "USERACCOUNT")
@NamedQueries({
        @NamedQuery(name = "findUserByUsername",
                query = "SELECT u FROM UserAccount u WHERE LOWER(u.username) = LOWER(:username)")
})
public class UserAccount implements Serializable {

	private static final long serialVersionUID = -8514552901525533422L;
	
	@Id
    @Column(name = "USERNAME")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{pattern.letter.number.underscore}")
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotNull(message = "{not.null}")
    private String password;
    @OneToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;
    @OneToOne(cascade=CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public UserAccount() {

    }

    public UserAccount(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }

	public Contact getContact() {
		return contact;
	}

	public void setContact(Contact contact) {
		this.contact = contact;
	}
}
