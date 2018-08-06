package com.coachingeleven.coachingsoftware.application.service;

import static javax.ejb.TransactionAttributeType.REQUIRED;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import com.coachingeleven.coachingsoftware.application.exception.ExtendedTIPSNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.ExtendedTIPS;
import com.coachingeleven.coachingsoftware.persistence.repository.ExtendedTIPSRepository;

@LocalBean
@Stateless(name = "ExtendedTIPSService")
@TransactionAttribute(REQUIRED)
public class ExtendedTIPSService implements ExtendedTIPSServiceRemote {

	private static final Logger logger = Logger.getLogger(ExtendedTIPSService.class.getName());
	
	@EJB
	private ExtendedTIPSRepository extendedTIPSRepository;
	
	@Override
	public ExtendedTIPS createExtendedTIPS(ExtendedTIPS extendedTIPS) throws ExtendedTIPSNotFoundException {
		logger.log(Level.INFO, "Adding extended TIPS for player with id ''{0}''", extendedTIPS.getPlayer().getID());
		if (extendedTIPSRepository.find(extendedTIPS.getID()) != null) {
			logger.log(Level.INFO, "ExtendedTIPS with same id already exists");
			throw new ExtendedTIPSNotFoundException();
		}
		return extendedTIPSRepository.persist(extendedTIPS);
	}

}
