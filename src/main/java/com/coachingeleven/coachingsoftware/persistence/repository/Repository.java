/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Repository<T> {

	private static final String PERSISTENCE_UNIT = "coachingeleven";
	protected final EntityManager entityManager;

	public Repository() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT);
		entityManager = entityManagerFactory.createEntityManager();
	}

	public T persist(T entity) {
		entityManager.getTransaction().begin();
		entityManager.persist(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public T find(Class<T> type, Object id) {
		return entityManager.find(type, id);
	}

	public T update(T entity) {
		entityManager.getTransaction().begin();
		entity = entityManager.merge(entity);
		entityManager.getTransaction().commit();
		return entity;
	}

	public boolean delete(Class<T> type, Object id) {
		T entity = entityManager.find(type, id);
		if (entity == null) {
			return false;
		}
		entityManager.getTransaction().begin();
		entityManager.remove(entity);
		entityManager.getTransaction().commit();
		return true;
	}
}
