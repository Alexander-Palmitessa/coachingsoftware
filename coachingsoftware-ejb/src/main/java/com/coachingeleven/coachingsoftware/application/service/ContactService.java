package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.repository.ContactRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "teamContactService")
@TransactionAttribute(REQUIRED)
public class ContactService implements ContactServiceRemote {

	private static final Logger logger = Logger.getLogger(ContactService.class.getName());

	@EJB
	private ContactRepository teamContactRepository;

	@Override
	public Contact createTeamContact(Contact teamContact) throws TeamContactAlreadyExistsException {
		logger.log(Level.INFO, "Creating teamcontact with id ''{0}''", teamContact.getID());
		if (teamContactRepository.find(Contact.class, teamContact.getID()) != null) {
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
	public Contact findTeamContact(int id) throws TeamContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with ID ''{0}''", id);
		Contact teamContact = teamContactRepository.find(id);
		if (teamContact == null) {
			logger.log(Level.INFO, "Teamcontact not found");
			throw new TeamContactNotFoundException();
		}
		return teamContact;
	}

	@Override
	public Contact findTeamContact(String email) throws TeamContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with email ''{0}''", email);
		Contact teamContact = teamContactRepository.find(email);
		if (teamContact == null) {
			logger.log(Level.INFO, "Player not found");
			throw new TeamContactNotFoundException();
		}
		return teamContact;
	}
}
