package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.User;

import javax.ejb.Remote;

@Remote
public interface UserServiceRemote {
    User createUser(User user) throws UserAlreadyExistsException;
    User findUser(String username) throws UserNotFoundException;
    boolean checkPassword(String username, String password);
    User changePassword(String username, String oldPassword, String newPassword);
    String hashPassword(String plainPassword);
}
