package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.io.Serializable;

@Entity
@Table(name = "USERACCOUNT")
@NamedQueries({
        @NamedQuery(name = "findUserByUsername",
                query = "SELECT u FROM UserAccount u WHERE LOWER(u.username) = LOWER(:username)"),
        @NamedQuery(name = "findUserByMail",
        		query = "SELECT u FROM UserAccount u WHERE LOWER(u.email) = LOWER(:email)")
})
public class UserAccount implements Serializable {

    @Id
    @Column(name = "USERNAME")
    @Pattern(regexp = "^[a-zA-Z0-9_]+$", message = "{pattern.letter.number.underscore}")
    private String username;
    @Column(name = "PASSWORD", nullable = false)
    @NotNull(message = "{not.null}")
    private String password;
    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$", message = "{pattern.email}")
    @NotNull(message = "{not.null}")
    private String email;
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "TEAM_ID")
    private Team team;

    public UserAccount() {

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
