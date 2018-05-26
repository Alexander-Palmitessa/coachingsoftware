/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

import java.util.List;

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
	
	@TransactionAttribute(SUPPORTS)
	public List<Player> findCurrentPlayersByTeam(int teamId) {
		try {
			TypedQuery<Player> query = entityManager.createNamedQuery("findPlayerByCurrentTeamId", Player.class);
			query.setParameter("teamId", teamId);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
	@TransactionAttribute(SUPPORTS)
	public List<Player> findHistoryPlayersByTeam(int teamId) {
		try {
			TypedQuery<Player> query = entityManager.createNamedQuery("findHistoryPlayerByTeamId", Player.class);
			query.setParameter("teamId", teamId);
			return query.getResultList();
		} catch (NoResultException ex) {
			return null;
		}
	}
	
}
