/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.persistence.repository;

import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import static javax.ejb.TransactionAttributeType.SUPPORTS;

@Stateless
public class EvaluationTalkRepository extends Repository<EvaluationTalk> {

	@TransactionAttribute(SUPPORTS)
	public EvaluationTalk find(int id) {
		return super.find(EvaluationTalk.class, id);
	}
}
