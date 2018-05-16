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
			TypedQuery<UserAccount> query = entityManager.createNamedQuery("findUser", UserAccount.class);
			query.setParameter("username", username);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
    }

    @TransactionAttribute(SUPPORTS)
    public UserAccount findByMail(String email) {
        return entityManager.createQuery("SELECT u FROM UserAccount u WHERE LOWER(u.email) = " +
                "LOWER(:email)", UserAccount.class).setParameter("email", email).getSingleResult();
    }
}
