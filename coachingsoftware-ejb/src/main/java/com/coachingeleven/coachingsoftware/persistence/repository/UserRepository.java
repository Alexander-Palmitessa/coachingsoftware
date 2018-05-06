package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.User;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class UserRepository extends Repository<User> {

    @TransactionAttribute(SUPPORTS)
    public User find(String username) {
        return entityManager.createQuery("SELECT u FROM User u WHERE LOWER(u.username) = " +
                "LOWER(:username)", User.class).setParameter("username", username).getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    public User findByMail(String email) {
        return entityManager.createQuery("SELECT u FROM User u WHERE LOWER(u.email) = " +
                "LOWER(:email)", User.class).setParameter("email", email).getSingleResult();
    }
}
