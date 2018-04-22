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
import com.coachingeleven.coachingsoftware.persistence.repository.EvaluationTalkRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "PlayerEvaluationService")
@TransactionAttribute(REQUIRED)
public class PlayerEvaluationService implements PlayerEvaluationServiceRemote {

	private static final Logger logger = Logger.getLogger(PlayerEvaluationService.class.getName());

	@EJB
	private EvaluationTalkRepository evaluationTalkRepository;


	@Override
	public void createEvaluationTalk(EvaluationTalk evaluationTalk) throws EvaluationTalkAlreadyExistsException {
		logger.log(Level.INFO, "Adding evluation talk for player with id ''{0}''", evaluationTalk.getPlayer().getID());
		if (evaluationTalkRepository.find(evaluationTalk.getID()) != null) {
			logger.log(Level.INFO, "Evaluation talk with same id already exists");
			throw new EvaluationTalkAlreadyExistsException();
		}
		evaluationTalkRepository.persist(evaluationTalk);
	}

	@Override
	public EvaluationTalk findEvaluationTalk(int evaluationTalkID) throws EvaluationTalkNotFoundException {
		logger.log(Level.INFO, "Finding evaluation talk with id ''{0}''", evaluationTalkID);
		EvaluationTalk evaluationTalk = evaluationTalkRepository.find(EvaluationTalk.class, evaluationTalkID);
		if (evaluationTalk == null) {
			logger.log(Level.INFO, "Evaluation talk not found");
			throw new EvaluationTalkNotFoundException();
		}
		return evaluationTalk;
	}

	@Override
	public List<EvaluationTalk> searchPlayerEvaluationTalks(int playerID) {
		return null;
		//TODO: IM PLAYER SERVICE?
	}
}
