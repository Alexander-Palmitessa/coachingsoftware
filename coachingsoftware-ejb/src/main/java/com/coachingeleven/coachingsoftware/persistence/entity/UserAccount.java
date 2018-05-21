package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "USERACCOUNT")
@NamedQueries({
	@NamedQuery(name = "findUser",
		query = "SELECT c FROM UserAccount c WHERE LOWER(c.username) = LOWER(:username)")
})
public class UserAccount implements Serializable {

    @Id
    @Column(name = "USERNAME")
    @Pattern(regexp = "^[a-zA-Z0-9_\\s]+$")
    String username;
    @Column(name = "PASSWORD", nullable = false)
    String password;
    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$")
    String email;
    @OneToOne
    @JoinColumn(name="TEAM_ID")
    private Team team;

    public UserAccount(){

    }

    public UserAccount(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
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
