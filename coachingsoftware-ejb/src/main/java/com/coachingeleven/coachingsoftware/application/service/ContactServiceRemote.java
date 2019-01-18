package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.ContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.ContactNotFoundException;
import com.coachingeleven.coachingsoftware.application.exception.NoTeamAssignedException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;
import com.coachingeleven.coachingsoftware.persistence.entity.Team;

import java.util.List;

import javax.ejb.Remote;

@Remote
public interface ContactServiceRemote {
	Contact createContact(Contact contact) throws ContactAlreadyExistsException;
	Contact findContact(int id) throws ContactNotFoundException;
	Contact findContact(String email) throws ContactNotFoundException;
	Team findAssignedTeam(Contact contact) throws NoTeamAssignedException;
	void update(Contact contact);
	List<Contact> findAll();
}
