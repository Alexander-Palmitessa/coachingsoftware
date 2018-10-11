package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

public class TeamContactRepository extends Repository<TeamContact> {

	@TransactionAttribute(SUPPORTS)
	public TeamContact find(int id) {
		return super.find(TeamContact.class, id);
	}

	@TransactionAttribute(SUPPORTS)
	public TeamContact find(String email) {
		try {
			TypedQuery<TeamContact> query = entityManager.createNamedQuery("findTeamContact", TeamContact.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
}
