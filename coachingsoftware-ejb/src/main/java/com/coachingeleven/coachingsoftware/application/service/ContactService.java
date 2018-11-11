package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.NoTeamAssignedException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;
import com.coachingeleven.coachingsoftware.persistence.repository.ContactRepository;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import static javax.ejb.TransactionAttributeType.REQUIRED;

@LocalBean
@Stateless(name = "contactService")
@TransactionAttribute(REQUIRED)
public class ContactService implements ContactServiceRemote {

	private static final Logger logger = Logger.getLogger(ContactService.class.getName());

	@EJB
	private ContactRepository contactRepository;

	@Override
	public Contact createContact(Contact contact) throws ContactAlreadyExistsException {
		logger.log(Level.INFO, "Creating contact with id ''{0}''", contact.getID());
		if (contactRepository.find(Contact.class, contact.getID()) != null) {
			logger.log(Level.INFO, "Teamcontact with same id already exists");
			throw new ContactAlreadyExistsException();
		} else if(contact.getEmail() != null){
			if(contactRepository.find(contact.getEmail()) != null) {
				logger.log(Level.INFO, "Contact with same email already exists");
				throw new ContactAlreadyExistsException();
			}
		}
		return contactRepository.persist(contact);
	}

	@Override
	public Contact findContact(int id) throws ContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with ID ''{0}''", id);
		Contact teamContact = contactRepository.find(id);
		if (teamContact == null) {
			logger.log(Level.INFO, "Teamcontact not found");
			throw new ContactNotFoundException();
		}
		return teamContact;
	}

	@Override
	public Contact findContact(String email) throws ContactNotFoundException {
		logger.log(Level.INFO, "Finding teamcontact with email ''{0}''", email);
		Contact teamContact = contactRepository.find(email);
		if (teamContact == null) {
			logger.log(Level.INFO, "Player not found");
			throw new ContactNotFoundException();
		}
		return teamContact;
	}

	@Override
	public Team findAssignedTeam(Contact contact) throws NoTeamAssignedException {
		contact = contactRepository.find(contact.getID());
		if(contact != null && !contact.getTeamContacts().isEmpty()) {
			for(TeamContact teamContact : contact.getTeamContacts()) {
				if(teamContact.getLeaveDate() == null) {
					return teamContact.getTeam();
				}
			}
			throw new NoTeamAssignedException();
		} else {
			throw new NoTeamAssignedException();
		}
	}

	@Override
	public void update(Contact contact) {
		contactRepository.update(contact);
	}
	
	@Override
	public List<Contact> findAll() {
		return contactRepository.findAll(Contact.class);
	}
}
