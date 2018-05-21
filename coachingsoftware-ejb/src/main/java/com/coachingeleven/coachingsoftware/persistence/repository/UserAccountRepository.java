package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.UserAccount;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class UserAccountRepository extends Repository<UserAccount> {

    @TransactionAttribute(SUPPORTS)
    public UserAccount find(String username) {
    	try {
			TypedQuery<UserAccount> query = entityManager.createNamedQuery("findUserByUsername", UserAccount.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
    }

    @TransactionAttribute(SUPPORTS)
    public UserAccount findByMail(String email) {
    	try {
			TypedQuery<UserAccount> query = entityManager.createNamedQuery("findUserByMail", UserAccount.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
    }
}
