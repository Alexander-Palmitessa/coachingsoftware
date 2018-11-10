package com.coachingeleven.coachingsoftware.application.service;

import javax.ejb.Remote;

import com.coachingeleven.coachingsoftware.application.exception.TeamContactAlreadyExistsException;
import com.coachingeleven.coachingsoftware.persistence.entity.TeamContact;

@Remote
public interface TeamContactServiceRemote {
	
	TeamContact createTeamContact(TeamContact teamContact) throws TeamContactAlreadyExistsException;

}
