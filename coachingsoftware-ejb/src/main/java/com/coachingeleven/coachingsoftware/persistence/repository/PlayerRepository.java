/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import com.coachingeleven.coachingsoftware.persistence.entity.Player;

@Stateless
public class PlayerRepository extends Repository<Player> {
	
	@TransactionAttribute(SUPPORTS)
	public Player find(int id) {
		return super.find(Player.class, id);
	}
	
	@TransactionAttribute(SUPPORTS)
	public Player find(String email) {
		try {
			TypedQuery<Player> query = entityManager.createNamedQuery("findPlayer", Player.class);
			query.setParameter("email", email);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
