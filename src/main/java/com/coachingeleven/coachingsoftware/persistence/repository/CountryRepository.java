/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Country;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class CountryRepository extends Repository<Country>{

	@TransactionAttribute(SUPPORTS)
	public Country find(String name) {
		return entityManager.createQuery("select c from Country c where c.name = " +
				":name", Country.class).setParameter("name", name).getSingleResult();
	}

}
