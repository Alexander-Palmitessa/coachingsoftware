/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Club;

public class ClubRepository extends Repository<Club> {

	public Club find(String name) {
		return entityManager.createQuery("select c from Club c where c.name = " +
				":name", Club.class).setParameter("name", name).getSingleResult();
	}
}