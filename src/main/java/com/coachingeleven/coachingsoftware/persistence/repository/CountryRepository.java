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
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class CountryRepository extends Repository<Country>{

	@TransactionAttribute(SUPPORTS)
	public Country find(String name) {
		try {
			TypedQuery<Country> query = entityManager.createNamedQuery("findCountry", Country.class);
			query.setParameter("countryname", name);
			return query.getSingleResult();
		} catch (NoResultException ex) {
			return null;
		}
	}

}
