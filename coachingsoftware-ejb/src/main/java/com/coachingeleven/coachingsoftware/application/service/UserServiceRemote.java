package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
	public UserAccount createUser(UserAccount userAccount) throws UserAlreadyExistsException;
    public UserAccount findUser(String username) throws UserNotFoundException;
    public boolean authenticate(String password, String token);
    public UserAccount changePassword(String username, String oldPassword, String newPassword);
    public String hashPassword(String plainPassword);
    public void updateUser(UserAccount user);
}
