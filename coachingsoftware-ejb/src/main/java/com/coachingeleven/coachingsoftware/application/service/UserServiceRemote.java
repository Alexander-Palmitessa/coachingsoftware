package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
    UserAccount createUser(UserAccount userAccount) throws UserAlreadyExistsException;
    UserAccount findUser(String username) throws UserNotFoundException;
    public boolean authenticate(String password, String token);
    UserAccount changePassword(String username, String oldPassword, String newPassword);
    String hashPassword(String plainPassword);
    public void updateUser(UserAccount user);
}
