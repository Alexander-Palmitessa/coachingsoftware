/*
 * Copyright (c) 2018 Schildknecht Elias & Palmitessa Alexander, Berner Fachhochschule, Switzerland.
 *
 * Project 'com.coachingeleven.coachingsoftware Coaching Administration System'
 *
 * Distributable under GPL license. See terms of license at gnu.org.
 */

package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.EvaluationTalkAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.EvaluationTalkNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.EvaluationTalk;

import javax.ejb.Remote;
import java.util.List;

@Remote
public interface PlayerEvaluationServiceRemote {
	void createEvaluationTalk(EvaluationTalk evaluationTalk) throws EvaluationTalkAlreadyExistsException;
	EvaluationTalk findEvaluationTalk(int evaluationTalkID) throws EvaluationTalkNotFoundException;
	List<EvaluationTalk> searchPlayerEvaluationTalks(int playerID);
}
