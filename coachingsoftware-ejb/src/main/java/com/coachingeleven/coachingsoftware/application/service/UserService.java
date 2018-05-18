package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.UserAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.UserNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;
import com.coachingeleven.coachingsoftware.persistence.repository.UserAccountRepository;

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
    private UserAccountRepository userRepository;

    @Override
    public UserAccount createUser(UserAccount userAccount) throws UserAlreadyExistsException {
        logger.log(Level.INFO, "Creating userAccount with username " + userAccount.getUsername() + " and email " + userAccount.getEmail());
        if (userRepository.find(userAccount.getUsername()) != null) {
            logger.log(Level.INFO, "UserAccount with username " + userAccount.getUsername() + " already exists");
            throw new UserAlreadyExistsException();
        } else if (userRepository.findByMail(userAccount.getEmail()) != null) {
            logger.log(Level.INFO, "UserAccount with email " + userAccount.getEmail() + " already exists");
            throw new UserAlreadyExistsException();
        }
        userAccount.setPassword(hashPassword(userAccount.getPassword()));
        return userRepository.persist(userAccount);
    }

    @Override
    public UserAccount findUser(String username) throws UserNotFoundException {
        logger.log(Level.INFO, "Finding userAccount with username " + username);
        UserAccount userAccount = userRepository.find(username);
        if (userAccount == null) {
            logger.log(Level.INFO, "UserAccount not found");
            throw new UserNotFoundException();
        }
        return userAccount;
    }

    @Override
    public boolean checkPassword(String username, String password) {
        return false;
    }

    @Override
    public UserAccount changePassword(String username, String oldPassword, String newPassword) {
        return null;
    }

    @Override
    public String hashPassword(String plainPassword) {
        return null;
    }

	@Override
	public void updateUser(UserAccount user) {
		userRepository.update(user);
	}
}
