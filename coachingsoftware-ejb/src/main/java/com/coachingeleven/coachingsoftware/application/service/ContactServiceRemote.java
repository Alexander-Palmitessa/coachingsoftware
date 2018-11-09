package com.coachingeleven.coachingsoftware.application.service;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.application.exception.TeamContactNotFoundException;
import com.coachingeleven.coachingsoftware.persistence.entity.Contact;

import javax.ejb.Remote;

@Remote
public interface ContactServiceRemote {
	Contact createTeamContact(Contact teamContact) throws TeamContactAlreadyExistsException;
	Contact findTeamContact(int id) throws TeamContactNotFoundException;
	Contact findTeamContact(String email) throws TeamContactNotFoundException;
}
