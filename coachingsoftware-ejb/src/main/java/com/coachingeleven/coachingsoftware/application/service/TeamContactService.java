package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.repository.TeamContactRepository;

import javax.ejb.EJB;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TeamContactService implements TeamContactServiceRemote {

	private static final Logger logger = Logger.getLogger(TeamContactService.class.getName());

	@EJB
	private TeamContactRepository teamContactRepository;

	@Override
	public TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException {
		logger.log(Level.INFO, "Creating teamcontact with id ''{0}''", teamContact.getID());
		if (teamContactRepository.find(TeamContact.class, teamContact.getID()) != null) {
			logger.log(Level.INFO, "Teamcontact with same id already exists");
			throw new TeamContactAlreadyExistsException();
		} else if(teamContact.getEmail() != null){
			if(teamContactRepository.find(teamContact.getEmail()) != null) {
				logger.log(Level.INFO, "Teamcontact with same email already exists");
				throw new TeamContactAlreadyExistsException();
			}
		}
		return teamContactRepository.persist(teamContact);
	}

	@Override
	public TeamContact findTeamContact(int id) throws TeamContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with ID ''{0}''", id);
		TeamContact teamContact = teamContactRepository.find(id);
		if (teamContact == null) {
			logger.log(Level.INFO, "Teamcontact not found");
			throw new TeamContactNotFoundException();
		}
		return teamContact;
	}

	@Override
	public TeamContact findTeamContact(String email) throws TeamContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with email ''{0}''", email);
		TeamContact teamContact = teamContactRepository.find(email);
		if (teamContact == null) {
			logger.log(Level.INFO, "Player not found");
			throw new TeamContactNotFoundException();
		}
		return teamContact;
	}
}
