/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Country;

public class CountryRepository extends Repository<Country>{

	public Country find(String name) {
		return entityManager.createQuery("select c from Country c where c.name = " +
				":name", Country.class).setParameter("name", name).getSingleResult();
	}
}
