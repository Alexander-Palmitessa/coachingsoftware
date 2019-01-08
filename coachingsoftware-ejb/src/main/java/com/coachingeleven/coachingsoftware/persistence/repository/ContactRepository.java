package com.coachingeleven.coachingsoftware.persistence.repository;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Contact;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class ContactRepository extends Repository<Contact> {

	@TransactionAttribute(SUPPORTS)
	public Contact find(int id) {
		return super.find(Contact.class, id);
	}

	@TransactionAttribute(SUPPORTS)
	public Contact find(String email) {
		try {
			TypedQuery<Contact> query = entityManager.createNamedQuery("findContactByEmail", Contact.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
