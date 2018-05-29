/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import javax.ejb.TransactionAttribute;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import java.util.List;

import static javax.ejb.TransactionAttributeType.MANDATORY;
import static javax.ejb.TransactionAttributeType.SUPPORTS;

public class Repository<T> {

	@PersistenceContext(unitName = "coachingeleven")
	protected EntityManager entityManager;

	@TransactionAttribute(MANDATORY)
	public T persist(T entity) {
		entityManager.persist(entity);
		entityManager.flush();
		return entity;
	}

	@TransactionAttribute(SUPPORTS)
	public T find(Class<T> type, Object id) {
		return entityManager.find(type, id);
	}

	@TransactionAttribute(MANDATORY)
	public T update(T entity) {
		return entityManager.merge(entity);
	}

	@TransactionAttribute(MANDATORY)
	public boolean delete(Class<T> type, Object id) {
		T entity = entityManager.find(type, id);
		if (entity == null) {
			return false;
		}
		entityManager.remove(entity);
		entityManager.flush();
		return true;
	}

	@TransactionAttribute(SUPPORTS)
    public List<T> findAll(Class<T> type) {
		return entityManager.createQuery("Select t from " + type.getSimpleName() + " t").getResultList();
    }

    public void flush(){
		entityManager.flush();
	}
}
