package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class UserAccountRepository extends Repository<UserAccount> {

    @TransactionAttribute(SUPPORTS)
    public UserAccount find(String username) {
        return entityManager.createQuery("SELECT u FROM UserAccount u WHERE LOWER(u.username) = " +
                "LOWER(:username)", UserAccount.class).setParameter("username", username).getSingleResult();
    }

    @TransactionAttribute(SUPPORTS)
    public UserAccount findByMail(String email) {
        return entityManager.createQuery("SELECT u FROM UserAccount u WHERE LOWER(u.email) = " +
                "LOWER(:email)", UserAccount.class).setParameter("email", email).getSingleResult();
    }
}
