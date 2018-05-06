package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.User;
import com.coachingeleven.coachingsoftware.persistence.repository.UserRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "UserService")
@TransactionAttribute(REQUIRED)
public class UserService implements UserServiceRemote {

    private static final Logger logger = Logger.getLogger(UserService.class.getName());

    @EJB
    private UserRepository userRepository;

    @Override
    public User createUser(User user) throws UserAlreadyExistsException {
        logger.log(Level.INFO, "Creating user with username " + user.getUsername() + " and email " + user.getEmail());
        if (userRepository.find(user.getUsername()) != null) {
            logger.log(Level.INFO, "User with username " + user.getUsername() + " already exists");
            throw new UserAlreadyExistsException();
        } else if (userRepository.findByMail(user.getEmail()) != null) {
            logger.log(Level.INFO, "User with email " + user.getEmail() + " already exists");
            throw new UserAlreadyExistsException();
        }
        user.setPassword(hashPassword(user.getPassword()));
        return userRepository.persist(user);
    }

    @Override
    public User findUser(String username) throws UserNotFoundException {
        logger.log(Level.INFO, "Finding user with username " + username);
        User user = userRepository.find(username);
        if (user == null) {
            logger.log(Level.INFO, "User not found");
            throw new UserNotFoundException();
        }
        return user;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public User changePassword(String username, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public String hashPassword(String plainPassword) {
        return null;
    }
}
