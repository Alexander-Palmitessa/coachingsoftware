package com.coachingeleven.coachingsoftware.persistence.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "USERACCOUNT")
public class UserAccount implements Serializable {

    @Id
    @Column(name = "USERNAME")
    String username;
    @Column(name = "PASSWORD", nullable = false)
    String password;
    @Column(name = "USER_EMAIL", nullable = false, unique = true)
    String email;

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
}
