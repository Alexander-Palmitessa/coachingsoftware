/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class TeamRepository extends Repository<Team> {

    @TransactionAttribute(SUPPORTS)
    public Team find(String name) {
        return entityManager.createQuery("select t from Team t where t.name = " +
                ":name", Team.class).setParameter("name", name).getSingleResult();
    }
}
