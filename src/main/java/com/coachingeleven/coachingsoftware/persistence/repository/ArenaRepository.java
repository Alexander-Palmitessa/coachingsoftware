/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Arena;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.List;

public class ArenaRepository extends Repository<Arena> {

	public Arena find(String name) {
		return entityManager.createQuery("select a from Arena a where a.name = " +
				":name", Arena.class).setParameter("name", name).getSingleResult();
	}

	public List<Arena> search(String keywords) {
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<Arena> query = builder.createQuery(Arena.class);
		Root<Arena> arena = query.from(Arena.class);
		query.select(arena);
		String[] tokens = keywords.toLowerCase().split("\\s+");
		Predicate[] predicates = new Predicate[tokens.length];
		for (int i = 0; i < tokens.length; i++) {
			String pattern = "%" + tokens[i] + "%";
			predicates[i] = builder.or(
					builder.like(builder.lower(arena.<String>get("name")), pattern));
					//builder.like(builder.lower(arena.<String>get("street")), pattern), TODO: Kein Attribut bei Arena weil embedded
					//builder.like(builder.lower(arena.<String>get("country")), pattern), TODO: Kein Attribut bei Arena weil embedded
					//builder.like(builder.lower(arena.<String>get("city")), pattern)); TODO: Kein Attribut bei Arena weil embedded
		}
		query.where(builder.and(predicates));
		return entityManager.createQuery(query).getResultList();
	}
}
